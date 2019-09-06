package com.example.headsandhandstest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //==============================================================================================
    //                              Lifecycle
    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.authorization)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activityMainSignIn.setOnClickListener { showSnackBar(activityMainEmail.text.toString() + " " + activityMainPassword.text.toString()) }
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
                showSnackBar(getString(R.string.create))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
