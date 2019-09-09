package com.example.headsandhandstest.authorization.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.example.headsandhandstest.R
import com.example.headsandhandstest.authorization.application.AuthorizationInteractor
import com.example.headsandhandstest.kernel.ui.hideKeyboard
import com.example.headsandhandstest.kernel.ui.showKeyboard
import com.example.headsandhandstest.kernel.ui.showSnackBar
import kotlinx.android.synthetic.main.activity_authorization.*

class AuthorizationActivity : AppCompatActivity(), AuthorizationView {
    // TODO: Use IoC
    private val presenter: AuthorizationPresenter = AuthorizationPresenterImplementation(
        AuthorizationInteractor()
    )
    private val emailTextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable) = Unit
        override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) = Unit
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =
            presenter.changedEnteredEmail(p0.toString(), authorizationPassword.text.toString())
    }
    private val passwordTextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable) = Unit
        override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) = Unit
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =
            presenter.changedEnteredPassword(authorizationEmail.text.toString(), p0.toString())
    }

    // TODO: Use not deprecated Class
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(this) }

    //==============================================================================================
    //                              Lifecycle
    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        presenter.attachView(this)

        supportActionBar?.title = getString(R.string.authorization)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        authorizationSignIn.setOnClickListener { tryToSignIn() }
        authorizationEmail.addTextChangedListener(emailTextWatcher)
        authorizationPassword.addTextChangedListener(passwordTextWatcher)
        authorizationPassword.setOnEditorActionListener { _, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                tryToSignIn()
            }

            false
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    private fun tryToSignIn() {
        presenter.signIn(authorizationEmail.text.toString(), authorizationPassword.text.toString())
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
                presenter.close()
                true
            }
            R.id.action_create -> {
                presenter.create()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    //==============================================================================================
    //                              Validation
    //==============================================================================================
    override fun showEmailNotValidError() {
        authorizationEmailLayout.error = getString(R.string.not_valid_email)
    }

    override fun showPasswordMustContainAtLeastOneLowercaseLetterError() {
        authorizationPasswordLayout.error =
            getString(R.string.password_must_contain_at_one_lowercase_letter)
    }

    override fun showPasswordMustContainAtLeastOneCapitalLetterError() {
        authorizationPasswordLayout.error =
            getString(R.string.password_must_contain_at_one_capital_letter)
    }

    override fun showPasswordMustContainAtLeastOneDigitError() {
        authorizationPasswordLayout.error =
            getString(R.string.password_must_contain_at_least_one_digit)
    }

    override fun showPasswordMustContainAtLeastSixCharactersError() {
        authorizationPasswordLayout.error =
            getString(R.string.password_must_contain_at_least_six_characters)
    }

    override fun showCreateMessage() {
        showSnackBar(getString(R.string.create), authorizationContainer)
    }

    override fun clearEmailValidationMessage() {
        authorizationEmailLayout.error = null
    }

    override fun clearPasswordValidationMessage() {
        authorizationPasswordLayout.error = null
    }

    //==============================================================================================
    //                              Sign in success
    //==============================================================================================
    override fun showSignInSuccess() {
        showSnackBar(getString(R.string.sign_in_success), authorizationContainer)
    }

    //==============================================================================================
    //                              Close
    //==============================================================================================
    override fun close() {
        finish()
    }

    //==============================================================================================
    //                              Keyboard
    //==============================================================================================
    override fun showKeyboardOnEmail() {
        showKeyboard(authorizationEmail)
    }

    override fun showKeyboardOnPassword() {
        showKeyboard(authorizationPassword)
    }

    override fun closeKeyboard() {
        hideKeyboard()
    }

    //==============================================================================================
    //                              Sign in button
    //==============================================================================================
    override fun setSignInButtonEnabled() {
        authorizationSignIn.isEnabled = true
    }

    override fun setSignInButtonDisabled() {
        authorizationSignIn.isEnabled = false
    }

    //==============================================================================================
    //                              Loading Indicator
    //==============================================================================================
    override fun showLoadingIndicator() {
        progressDialog.show()
        progressDialog.setCancelable(false)
        progressDialog.setContentView(R.layout.dialog_progress)
    }

    override fun hideLoadingIndicator() {
        progressDialog.hide()
    }
}
