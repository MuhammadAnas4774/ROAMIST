package com.example.roamist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R

class ContactGuideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_guide)

        setupToolbarBack(findViewById(R.id.chat_toolbar))

        val guide = intent.getStringExtra(TripInfoActivity.EXTRA_GUIDE_NAME).orEmpty()
        val trip = intent.getStringExtra(TripInfoActivity.EXTRA_TRIP_TITLE).orEmpty()

        findViewById<TextView>(R.id.tv_chat_guide_name).text = guide.ifEmpty { "Your guide" }
        findViewById<TextView>(R.id.tv_chat_trip_context).text =
            if (trip.isNotEmpty()) "Re: $trip" else "Roamist message"

        findViewById<Button>(R.id.btn_send_message).setOnClickListener {
            val body = findViewById<EditText>(R.id.et_chat_message).text.toString().trim()
            if (body.isEmpty()) {
                Toast.makeText(this, "Write a message first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val send = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Roamist — $trip")
                putExtra(Intent.EXTRA_TEXT, "Hi $guide,\n\n$body\n\n— Sent via Roamist")
            }
            try {
                startActivity(Intent.createChooser(send, "Send message"))
            } catch (_: Exception) {
                Toast.makeText(this, "No app available to send", Toast.LENGTH_LONG).show()
            }
            finish()
        }
    }
}
