package com.example.roamist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.roamist.R

class NotifyAckActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_ack)

        val region = intent.getStringExtra(MapsActivity.EXTRA_REGION).orEmpty()
        val toolbar = findViewById<Toolbar>(R.id.notify_ack_toolbar)
        setupToolbarBack(toolbar)
        supportActionBar?.title = if (region.isNotEmpty()) "Alerts — $region" else "Map alerts"

        findViewById<Button>(R.id.btn_notify_done).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                forwardSession(this@NotifyAckActivity)
            })
            finish()
        }
    }
}
