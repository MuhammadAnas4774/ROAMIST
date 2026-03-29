package com.example.roamist.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R

class ChangePasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        setupToolbarBack(findViewById(R.id.password_toolbar))

        findViewById<Button>(R.id.btn_update_password).setOnClickListener {
            val cur = findViewById<EditText>(R.id.et_current_password).text.toString()
            val n1 = findViewById<EditText>(R.id.et_new_password).text.toString()
            val n2 = findViewById<EditText>(R.id.et_confirm_new_password).text.toString()
            when {
                cur.length < 6 -> Toast.makeText(this, "Enter current password", Toast.LENGTH_SHORT).show()
                n1.length < 6 -> Toast.makeText(this, "New password too short", Toast.LENGTH_SHORT).show()
                n1 != n2 -> Toast.makeText(this, "New passwords don’t match", Toast.LENGTH_SHORT).show()
                else -> {
                    Toast.makeText(this, "Password updated (demo)", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
