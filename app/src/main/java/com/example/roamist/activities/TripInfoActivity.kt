package com.example.roamist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R
import java.util.Locale

/** Trip detail — activity_settings.xml. Wires confirm, modify, contact guide, checkout flow. */
class TripInfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TRIP_TITLE = "extra_trip_title"
        const val EXTRA_TRIP_DATE = "extra_trip_date"
        const val EXTRA_TRIP_REGION = "extra_trip_region"
        const val EXTRA_TRIP_PRICE_PKR = "extra_trip_price_pkr"
        const val EXTRA_GUIDE_NAME = "extra_guide_name"
        const val EXTRA_DURATION = "extra_duration"
        const val EXTRA_TRAVELLERS = "extra_travellers"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setupToolbarBack(findViewById(R.id.trip_toolbar))

        val title = intent.getStringExtra(EXTRA_TRIP_TITLE).orEmpty()
        val date = intent.getStringExtra(EXTRA_TRIP_DATE).orEmpty()
        val region = intent.getStringExtra(EXTRA_TRIP_REGION).orEmpty()
        val pricePkr = intent.getIntExtra(EXTRA_TRIP_PRICE_PKR, 85_000)
        val guide = intent.getStringExtra(EXTRA_GUIDE_NAME).orEmpty()
        val duration = intent.getStringExtra(EXTRA_DURATION).orEmpty().ifEmpty { "7 Days" }
        val travellers = intent.getStringExtra(EXTRA_TRAVELLERS).orEmpty().ifEmpty { "3 Pax" }

        supportActionBar?.title = title.ifEmpty { "Trip Info" }

        findViewById<TextView>(R.id.tv_destination_name).text = title.ifEmpty { "Your trip" }
        findViewById<TextView>(R.id.tv_destination_region).text =
            if (region.isNotEmpty()) region else "Pakistan"
        findViewById<TextView>(R.id.tv_depart_date).text = date.ifEmpty { "TBC" }
        findViewById<TextView>(R.id.tv_total_cost).text =
            String.format(Locale.getDefault(), "PKR %,d", pricePkr).replace(',', '.')
        findViewById<TextView>(R.id.tv_guide_name).text = guide.ifEmpty { "Local guide" }
        findViewById<TextView>(R.id.tv_duration).text = duration
        findViewById<TextView>(R.id.tv_travellers).text = travellers

        findViewById<Button>(R.id.btn_contact_guide).setOnClickListener {
            startActivity(Intent(this, ContactGuideActivity::class.java).apply {
                putExtra(EXTRA_GUIDE_NAME, guide.ifEmpty { "Guide" })
                putExtra(EXTRA_TRIP_TITLE, title)
                forwardSession(this@TripInfoActivity)
            })
        }

        findViewById<Button>(R.id.btn_modify_trip).setOnClickListener {
            startActivity(Intent(this, ModifyTripActivity::class.java).apply {
                putExtra(EXTRA_TRIP_TITLE, title)
                putExtra(EXTRA_TRIP_DATE, date)
                putExtra(EXTRA_TRIP_REGION, region)
                putExtra(EXTRA_GUIDE_NAME, guide)
                putExtra(EXTRA_TRIP_PRICE_PKR, pricePkr)
                putExtra(EXTRA_DURATION, duration)
                putExtra(EXTRA_TRAVELLERS, travellers)
                forwardSession(this@TripInfoActivity)
            })
        }

        findViewById<Button>(R.id.btn_confirm_trip).setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java).apply {
                putExtra(EXTRA_TRIP_TITLE, title)
                putExtra(EXTRA_TRIP_REGION, region)
                putExtra(EXTRA_TRIP_DATE, date)
                putExtra(EXTRA_GUIDE_NAME, guide)
                putExtra(EXTRA_TRIP_PRICE_PKR, pricePkr)
                forwardSession(this@TripInfoActivity)
            })
        }
    }
}
