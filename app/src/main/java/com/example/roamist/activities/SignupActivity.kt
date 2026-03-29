package com.example.roamist.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R

/**
 * Sign-up flow — on success launches [MainActivity] (magazine) with the same Intent Extra keys as [LoginActivity] (F1 pattern).
 */
class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        setupToolbarBack(findViewById(R.id.signup_toolbar), getString(R.string.signup_title))

        val etName = findViewById<EditText>(R.id.et_full_name)
        val etEmail = findViewById<EditText>(R.id.et_email_signup)
        val etPhone = findViewById<EditText>(R.id.et_phone_signup)
        val etPass = findViewById<EditText>(R.id.et_password_signup)
        val etConfirm = findViewById<EditText>(R.id.et_confirm_password)

        findViewById<TextView>(R.id.tv_go_to_login).setOnClickListener { finish() }

        findViewById<Button>(R.id.btn_google_signup).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com/signup")))
        }

        findViewById<Button>(R.id.btn_signup).setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val p1 = etPass.text.toString().trim()
            val p2 = etConfirm.text.toString().trim()

            when {
                name.isEmpty() -> etName.error = "Name is required"
                email.isEmpty() -> etEmail.error = "Email is required"
                phone.isEmpty() -> etPhone.error = "Phone is required"
                p1.length < 6 -> etPass.error = "At least 6 characters"
                p1 != p2 -> etConfirm.error = "Passwords do not match"
                else -> {
                    val username = name.split(" ").firstOrNull()
                        ?.replaceFirstChar { it.uppercaseChar() } ?: "Traveller"
                    val token = "RMST-${System.currentTimeMillis()}"
                    startActivity(Intent(this, MainActivity::class.java).apply {
                        putExtra(LoginActivity.EXTRA_USERNAME, username)
                        putExtra(LoginActivity.EXTRA_SESSION_TOKEN, token)
                        putExtra(LoginActivity.EXTRA_GREETING, "Welcome, $username! Explore Pakistan 🌟")
                    })
                    finish()
                }
            }
        }
    }
}
