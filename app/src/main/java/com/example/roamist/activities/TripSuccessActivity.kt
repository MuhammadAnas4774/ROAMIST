package com.example.roamist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.roamist.R

class TripSuccessActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SCREEN_MODE = "success_mode"
        const val MODE_PAID = "paid"
        const val MODE_REVIEW = "review"
        const val MODE_CANCEL = "cancel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_confirmed)

        val toolbar = findViewById<Toolbar>(R.id.success_toolbar)
        setupToolbarBack(toolbar)
        toolbar.setNavigationOnClickListener { goExplore() }

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE).orEmpty()
        val title = intent.getStringExtra(TripInfoActivity.EXTRA_TRIP_TITLE).orEmpty()

        val titleView = findViewById<TextView>(R.id.tv_success_title)
        val msgView = findViewById<TextView>(R.id.tv_success_message)

        when (mode) {
            MODE_REVIEW -> {
                supportActionBar?.title = "Thank you"
                titleView.text = "Review submitted!"
                msgView.text =
                    "Thanks for helping the Roamist community discover Pakistan. Your notes for ${title.ifEmpty { "this trip" }} are live."
            }
            MODE_CANCEL -> {
                supportActionBar?.title = "Cancelled"
                titleView.text = "Booking cancelled"
                msgView.text =
                    "We’ve noted the cancellation for ${title.ifEmpty { "your trip" }}. Demo build — no refund simulation."
            }
            else -> {
                supportActionBar?.title = "Success"
                titleView.text = "Trip confirmed!"
                msgView.text =
                    "Payment received via ${intent.getStringExtra(CheckoutFlowExtras.EXTRA_PAYMENT_METHOD).orEmpty()}. Pack your bags — ${title.ifEmpty { "adventure" }} awaits."
            }
        }

        findViewById<Button>(R.id.btn_success_trips).setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                putExtra(DashboardActivity.EXTRA_INITIAL_TAB, DashboardActivity.TAB_TRIPS)
                putExtra(LoginActivity.EXTRA_USERNAME, intent.getStringExtra(LoginActivity.EXTRA_USERNAME))
                putExtra(LoginActivity.EXTRA_SESSION_TOKEN, intent.getStringExtra(LoginActivity.EXTRA_SESSION_TOKEN))
                putExtra(LoginActivity.EXTRA_GREETING, intent.getStringExtra(LoginActivity.EXTRA_GREETING))
            })
            finish()
        }

        findViewById<Button>(R.id.btn_success_home).setOnClickListener { goExplore() }
    }

    private fun goExplore() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(LoginActivity.EXTRA_USERNAME, intent.getStringExtra(LoginActivity.EXTRA_USERNAME))
            putExtra(LoginActivity.EXTRA_SESSION_TOKEN, intent.getStringExtra(LoginActivity.EXTRA_SESSION_TOKEN))
            putExtra(LoginActivity.EXTRA_GREETING, intent.getStringExtra(LoginActivity.EXTRA_GREETING))
        })
        finish()
    }
}
