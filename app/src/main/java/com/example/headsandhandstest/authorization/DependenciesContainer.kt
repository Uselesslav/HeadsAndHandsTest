package com.example.headsandhandstest.authorization

import android.content.Context
import com.example.headsandhandstest.authorization.application.AuthorizationInteractor
import com.example.headsandhandstest.authorization.application.WeatherRepository
import com.example.headsandhandstest.authorization.infrastracture.WeatherRepositoryImplementation
import com.example.headsandhandstest.authorization.ui.AuthorizationPresenter
import com.example.headsandhandstest.authorization.ui.AuthorizationPresenterImplementation
import com.example.headsandhandstest.kernel.infrastructure.ConnectionCheckInterceptor
import com.example.headsandhandstest.kernel.infrastructure.ConnectionManager
import com.example.headsandhandstest.kernel.infrastructure.LoggingInterceptor
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class DependenciesContainer {
    companion object {
        private const val URL = "https://api.apixu.com/v1/current.json"
        private const val TOKEN = "105a2463d75842a1a3c94134190909"
    }

    private val authorizationModule = module {
        factory { AuthorizationInteractor(get()) }
        factory<AuthorizationPresenter> { AuthorizationPresenterImplementation(get()) }
    }

    private val weatherModule = module {
        factory<WeatherRepository> { WeatherRepositoryImplementation(get(), URL, TOKEN, get()) }
    }

    private val kernelInfrastructureModule = module {
        factory {
            listOf(
                LoggingInterceptor(),
                ConnectionCheckInterceptor(ConnectionManager(get()))
            )
        }
        factory { ConnectionManager(get()) }
        factory { createOkHttpClient(get()) }
        factory { Gson() }
    }

    private fun createOkHttpClient(interceptors: List<Interceptor>): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        interceptors.forEach { okHttpClientBuilder.addInterceptor(it) }

        return okHttpClientBuilder.build()
    }

    fun init(context: Context) {
        startKoin {
            androidContext(context)
            modules(
                listOf(
                    authorizationModule,
                    kernelInfrastructureModule,
                    weatherModule
                )
            )
        }
    }
}