package com.example.roamist.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.roamist.R

/** Consistent orange back arrow; uses system back (fragment stack, then activity finish). */
fun AppCompatActivity.setupToolbarBack(toolbar: Toolbar, title: String? = null) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    toolbar.setNavigationIcon(R.drawable.ic_nav_back)
    if (title != null) supportActionBar?.title = title
    toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
}
