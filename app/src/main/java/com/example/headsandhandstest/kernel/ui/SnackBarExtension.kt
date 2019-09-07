package com.example.headsandhandstest.kernel.ui

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackBar(message: String, view: View = window.decorView) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}