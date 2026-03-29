package com.example.roamist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R
import kotlin.math.max

class ModifyTripActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_trip)

        setupToolbarBack(findViewById(R.id.modify_toolbar))

        val title = intent.getStringExtra(TripInfoActivity.EXTRA_TRIP_TITLE).orEmpty()
        val date = intent.getStringExtra(TripInfoActivity.EXTRA_TRIP_DATE).orEmpty()
        val region = intent.getStringExtra(TripInfoActivity.EXTRA_TRIP_REGION).orEmpty()
        val guide = intent.getStringExtra(TripInfoActivity.EXTRA_GUIDE_NAME).orEmpty()
        val basePrice = intent.getIntExtra(TripInfoActivity.EXTRA_TRIP_PRICE_PKR, 45_000)
        val duration = intent.getStringExtra(TripInfoActivity.EXTRA_DURATION).orEmpty()
        val travellers = intent.getStringExtra(TripInfoActivity.EXTRA_TRAVELLERS).orEmpty()

        findViewById<TextView>(R.id.tv_modify_trip_title).text = title.ifEmpty { "Your trip" }
        findViewById<EditText>(R.id.et_modify_date).setText(date)
        findViewById<EditText>(R.id.et_modify_duration).setText(duration.filter { it.isDigit() }.ifEmpty { "5" })
        findViewById<EditText>(R.id.et_modify_travellers).setText(travellers.filter { it.isDigit() }.ifEmpty { "3" })

        findViewById<Button>(R.id.btn_modify_cancel).setOnClickListener { finish() }

        findViewById<Button>(R.id.btn_save_changes).setOnClickListener {
            val newDate = findViewById<EditText>(R.id.et_modify_date).text.toString().trim().ifEmpty { date }
            val days = findViewById<EditText>(R.id.et_modify_duration).text.toString().toIntOrNull() ?: 5
            val pax = findViewById<EditText>(R.id.et_modify_travellers).text.toString().toIntOrNull() ?: 3
            val adjustedPrice = basePrice + max(0, days - 5) * 1_800 + max(0, pax - 2) * 2_500

            startActivity(Intent(this, CheckoutActivity::class.java).apply {
                putExtra(TripInfoActivity.EXTRA_TRIP_TITLE, title)
                putExtra(TripInfoActivity.EXTRA_TRIP_REGION, region)
                putExtra(TripInfoActivity.EXTRA_TRIP_DATE, newDate)
                putExtra(TripInfoActivity.EXTRA_GUIDE_NAME, guide)
                putExtra(TripInfoActivity.EXTRA_TRIP_PRICE_PKR, adjustedPrice)
                forwardSession(this@ModifyTripActivity)
            })
            finish()
        }
    }
}
