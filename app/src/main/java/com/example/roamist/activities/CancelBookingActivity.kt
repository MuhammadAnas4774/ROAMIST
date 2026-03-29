package com.example.roamist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R

class CancelBookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel_booking)

        setupToolbarBack(findViewById(R.id.cancel_toolbar))

        val title = intent.getStringExtra(TripInfoActivity.EXTRA_TRIP_TITLE).orEmpty()
        findViewById<TextView>(R.id.tv_cancel_trip_name).text = title.ifEmpty { "This booking" }

        findViewById<Button>(R.id.btn_keep_booking).setOnClickListener { finish() }

        findViewById<Button>(R.id.btn_confirm_cancel).setOnClickListener {
            startActivity(Intent(this, TripSuccessActivity::class.java).apply {
                putExtra(TripSuccessActivity.EXTRA_SCREEN_MODE, TripSuccessActivity.MODE_CANCEL)
                putExtra(TripInfoActivity.EXTRA_TRIP_TITLE, title)
                forwardSession(this@CancelBookingActivity)
            })
            finish()
        }
    }
}
