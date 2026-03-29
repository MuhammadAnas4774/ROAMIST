package com.example.roamist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R

/** Uses res/layout/view_notification_badge.xml (notifications feed UI). */
class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_notification_badge)

        setupToolbarBack(findViewById(R.id.notif_toolbar))

        findViewById<TextView>(R.id.btn_mark_all_read).setOnClickListener {
            findViewById<TextView>(R.id.tv_notif_unread_banner).text = "All caught up"
        }

        findViewById<Button>(R.id.btn_book_deal).setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java).apply {
                putExtra(TripInfoActivity.EXTRA_TRIP_TITLE, "Swat Valley — flash sale")
                putExtra(TripInfoActivity.EXTRA_TRIP_DATE, "Mar–Apr 2026 (flexible)")
                putExtra(TripInfoActivity.EXTRA_TRIP_REGION, "Khyber Pakhtunkhwa")
                putExtra(TripInfoActivity.EXTRA_GUIDE_NAME, "Roamist Deals desk")
                putExtra(TripInfoActivity.EXTRA_TRIP_PRICE_PKR, 18_500)
                forwardSession(this@NotificationsActivity)
            })
        }
    }
}
