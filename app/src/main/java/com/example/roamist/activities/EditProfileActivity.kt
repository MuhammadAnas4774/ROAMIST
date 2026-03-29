package com.example.roamist.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R

class EditProfileActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FULL_NAME = "extra_full_name"
        const val EXTRA_PHONE = "extra_phone"
        const val EXTRA_CITY = "extra_city"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        setupToolbarBack(findViewById(R.id.edit_profile_toolbar))

        findViewById<EditText>(R.id.et_profile_name).setText(intent.getStringExtra(EXTRA_FULL_NAME).orEmpty())
        findViewById<EditText>(R.id.et_profile_phone).setText(intent.getStringExtra(EXTRA_PHONE).orEmpty())
        findViewById<EditText>(R.id.et_profile_city).setText(intent.getStringExtra(EXTRA_CITY).orEmpty())

        findViewById<Button>(R.id.btn_save_profile).setOnClickListener {
            val name = findViewById<EditText>(R.id.et_profile_name).text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "Profile saved (demo) — $name", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
