package com.example.headsandhandstest.authorization

import android.content.Context
import com.example.headsandhandstest.BuildConfig
import com.example.headsandhandstest.authorization.application.AuthorizationInteractor
import com.example.headsandhandstest.authorization.application.EmailValidator
import com.example.headsandhandstest.authorization.application.PasswordValidator
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
        private const val URL = BuildConfig.WEATHER_URL
        private const val TOKEN = BuildConfig.WEATHER_TOKEN
    }

    private val authorizationModule = module {
        factory { AuthorizationInteractor(get(), get(), get()) }
        factory<AuthorizationPresenter> { AuthorizationPresenterImplementation(get()) }
        factory { EmailValidator() }
        factory { PasswordValidator() }
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