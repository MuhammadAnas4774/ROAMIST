package com.example.roamist.activities

import android.app.Activity
import android.content.Intent

/** Copies session extras from [from]’s intent into this intent (no static app state). */
fun Intent.forwardSession(from: Activity) {
    putExtra(LoginActivity.EXTRA_USERNAME, from.intent.getStringExtra(LoginActivity.EXTRA_USERNAME))
    putExtra(LoginActivity.EXTRA_SESSION_TOKEN, from.intent.getStringExtra(LoginActivity.EXTRA_SESSION_TOKEN))
    putExtra(LoginActivity.EXTRA_GREETING, from.intent.getStringExtra(LoginActivity.EXTRA_GREETING))
}
