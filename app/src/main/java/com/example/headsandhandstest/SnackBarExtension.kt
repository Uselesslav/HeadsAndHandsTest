package com.example.headsandhandstest

import android.app.Activity
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackBar(message: String) {
    Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT).show()
}