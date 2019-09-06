package com.example.headsandhandstest

import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_authorization.*

class AuthorizationActivity : AppCompatActivity() {

    //==============================================================================================
    //                              Lifecycle
    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        supportActionBar?.title = getString(R.string.authorization)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        authorizationSignIn.setOnClickListener { tryToSignIn() }
        authorizationPassword.setOnEditorActionListener { _, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                tryToSignIn()
            }

            false
        }
    }

    override fun onResume() {
        super.onResume()
        showKeyboard(authorizationEmail)
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    private fun tryToSignIn() {
        hideKeyboard()
        showSnackBar(
            authorizationEmail.text.toString() + " " + authorizationPassword.text.toString(),
            authorizationContainer
        )
    }

    //==============================================================================================
    //                              Menu
    //==============================================================================================
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_authorization, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_create -> {
                showSnackBar(getString(R.string.create), authorizationContainer)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
