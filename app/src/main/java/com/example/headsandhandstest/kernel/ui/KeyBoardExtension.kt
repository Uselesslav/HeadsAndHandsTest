package com.example.headsandhandstest.kernel.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Context.getInputMethodManager(): InputMethodManager =
    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Activity.showKeyboard(view: View) {
    view.post {
        view.requestFocus()
        getInputMethodManager().showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Activity.hideKeyboard() {
    getInputMethodManager().hideSoftInputFromWindow(
        window.decorView.windowToken,
        InputMethodManager.RESULT_UNCHANGED_SHOWN
    )
}