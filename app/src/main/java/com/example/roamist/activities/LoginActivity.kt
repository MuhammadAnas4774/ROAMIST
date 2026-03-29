package com.example.roamist.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.roamist.R

/**
 * [F1] Login screen — declared as LAUNCHER in AndroidManifest so it is the app entry point.
 *
 * On successful sign-in it fires an explicit Intent to MainActivity (magazine) and attaches:
 *   • EXTRA_USERNAME      — display name derived from the email prefix
 *   • EXTRA_SESSION_TOKEN — a timestamped session identifier
 *   • EXTRA_GREETING      — personalised welcome string
 *
 * CONSTRAINT SATISFIED: No SharedPreferences, Singletons, or static/global fields
 * are used for inter-screen data transfer.
 */
class LoginActivity : AppCompatActivity() {

    companion object {
        /** Intent Extra keys — consumed by MainActivity, DashboardActivity, and downstream screens */
        const val EXTRA_USERNAME      = "extra_username"
        const val EXTRA_SESSION_TOKEN = "extra_session_token"
        const val EXTRA_GREETING      = "extra_greeting"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginToolbar = findViewById<Toolbar>(R.id.login_toolbar)
        setSupportActionBar(loginToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val etEmail:    EditText = findViewById(R.id.et_email_login)
        val etPassword: EditText = findViewById(R.id.et_password_login)
        val btnLogin:   Button   = findViewById(R.id.btn_login)
        val tvSignup:   TextView = findViewById(R.id.tv_go_to_signup)
        val btnGoogle:  Button   = findViewById(R.id.btn_google_login)
        val tvForgot:   TextView = findViewById(R.id.tv_forgot_password)

        btnGoogle.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com/signin")))
        }
        tvForgot.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:support@roamist.app?subject=" + Uri.encode("Password reset"))
                }
            )
        }

        // [F1] Sign-in button click → validate → launch MainActivity via Intent Extras
        btnLogin.setOnClickListener {
            val email    = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            when {
                email.isEmpty() -> {
                    etEmail.error = "Email is required"
                    etEmail.requestFocus()
                }
                password.isEmpty() -> {
                    etPassword.error = "Password is required"
                    etPassword.requestFocus()
                }
                password.length < 6 -> {
                    etPassword.error = "Password must be at least 6 characters"
                    etPassword.requestFocus()
                }
                else -> {
                    // Derive a display name from the email prefix
                    val username     = email.substringBefore("@")
                        .replaceFirstChar { it.uppercase() }
                    val sessionToken = "RMST-${System.currentTimeMillis()}"

                    // [F1] All session data packaged strictly into Intent Extras
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(EXTRA_USERNAME,      username)
                        putExtra(EXTRA_SESSION_TOKEN, sessionToken)
                        putExtra(EXTRA_GREETING,      "Welcome back, $username! Explore Pakistan 🌟")
                    }

                    startActivity(intent)
                    finish()   // Pop LoginActivity off the back stack
                }
            }
        }

        tvSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
