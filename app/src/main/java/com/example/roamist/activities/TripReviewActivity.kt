package com.example.roamist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R

class TripReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_review)

        setupToolbarBack(findViewById(R.id.review_toolbar))

        val title = intent.getStringExtra(TripInfoActivity.EXTRA_TRIP_TITLE).orEmpty()
        findViewById<TextView>(R.id.tv_review_destination).text = title.ifEmpty { "Trip" }

        findViewById<Button>(R.id.btn_submit_review).setOnClickListener {
            val text = findViewById<EditText>(R.id.et_review_text).text.toString().trim()
            if (text.length < 8) {
                Toast.makeText(this, "Please write a few more words", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val stars = findViewById<RatingBar>(R.id.rating_bar).rating.toInt()
            Toast.makeText(this, "$stars★ review queued…", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, TripSuccessActivity::class.java).apply {
                putExtra(TripSuccessActivity.EXTRA_SCREEN_MODE, TripSuccessActivity.MODE_REVIEW)
                putExtra(TripInfoActivity.EXTRA_TRIP_TITLE, title)
                forwardSession(this@TripReviewActivity)
            })
            finish()
        }
    }
}
