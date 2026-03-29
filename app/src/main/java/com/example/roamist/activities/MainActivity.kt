package com.example.roamist.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.roamist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Magazine home — first screen after sign-in; bottom nav opens [DashboardActivity] without finishing this activity.
 */
class MainActivity : AppCompatActivity() {

    private var sessionUsername = ""
    private var sessionToken = ""
    private var sessionGreeting = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readSessionFromIntent()

        bindPreviewCard(R.id.include_card_hunza, "🏔️", "Hunza Valley", "Gilgit-Baltistan", "Mountains", 4.9f, 8500)
        bindPreviewCard(R.id.include_card_skardu, "🏕️", "Skardu", "Gilgit-Baltistan", "Mountains", 4.8f, 7200)
        bindPreviewCard(R.id.include_card_swat, "🏞️", "Swat Valley", "Khyber Pakhtunkhwa", "Valley", 4.6f, 6500)

        wirePreviewToTrip(R.id.include_card_hunza, "Hunza Valley", "Gilgit-Baltistan, Pakistan", "15–22 Mar 2026", 85_000, "Ali Nawaz")
        wirePreviewToTrip(R.id.include_card_skardu, "Skardu", "Gilgit-Baltistan, Pakistan", "01–08 Apr 2026", 72_000, "Imran Shah")
        wirePreviewToTrip(R.id.include_card_swat, "Swat Valley", "Khyber Pakhtunkhwa", "10–16 May 2026", 58_000, "Sana Malik")

        findViewById<CardView>(R.id.card_nearby_food).setOnClickListener {
            openTripFromMagazine("Food Street Lahore", "Lahore, Punjab", "Weekends 2026", 8_500, "Roamist City")
        }
        findViewById<CardView>(R.id.card_nearby_mohenjo).setOnClickListener {
            openTripFromMagazine("Mohenjo-daro", "Larkana, Sindh", "Heritage slot TBC", 12_000, "Heritage guide")
        }
        findViewById<CardView>(R.id.card_nearby_fairy).setOnClickListener {
            openTripFromMagazine("Fairy Meadows trek", "Gilgit-Baltistan", "Summer 2026", 48_000, "Mountain guide")
        }
        findViewById<CardView>(R.id.card_nearby_attabad).setOnClickListener {
            openTripFromMagazine("Attabad Lake", "Hunza, GB", "Boat season", 22_000, "Lake guide")
        }
        findViewById<CardView>(R.id.card_nearby_mosque).setOnClickListener {
            openTripFromMagazine("Badshahi Mosque", "Lahore, Punjab", "Open daily", 3_500, "City guide")
        }

        findViewById<ImageView>(R.id.btn_notifications).setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java).apply {
                putExtra(LoginActivity.EXTRA_USERNAME, sessionUsername)
                putExtra(LoginActivity.EXTRA_SESSION_TOKEN, sessionToken)
                putExtra(LoginActivity.EXTRA_GREETING, sessionGreeting)
            })
        }

        findViewById<FloatingActionButton>(R.id.fab_plan_trip).setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java).apply {
                putExtra(MapsActivity.EXTRA_REGION, "Gilgit-Baltistan")
                putExtra(LoginActivity.EXTRA_USERNAME, sessionUsername)
                putExtra(LoginActivity.EXTRA_SESSION_TOKEN, sessionToken)
                putExtra(LoginActivity.EXTRA_GREETING, sessionGreeting)
            })
        }

        findViewById<View>(R.id.layout_search).setOnClickListener {
            goDashboard(DashboardActivity.TAB_EXPLORE)
        }

        findViewById<TextView>(R.id.tv_see_all).setOnClickListener {
            goDashboard(DashboardActivity.TAB_EXPLORE)
        }

        findViewById<TextView>(R.id.tv_nearby_see_all).setOnClickListener {
            goDashboard(DashboardActivity.TAB_SAVED)
        }

        setupMainBottomNav()

        findViewById<TextView>(R.id.chip_all).setOnClickListener {
            goDashboard(DashboardActivity.TAB_EXPLORE)
        }
        findViewById<TextView>(R.id.chip_mountains).setOnClickListener {
            goDashboard(DashboardActivity.TAB_EXPLORE)
        }
        findViewById<TextView>(R.id.chip_culture).setOnClickListener {
            goDashboard(DashboardActivity.TAB_SAVED)
        }
        findViewById<TextView>(R.id.chip_beaches).setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java).apply {
                putExtra(MapsActivity.EXTRA_REGION, "Coastal Pakistan")
                putExtra(LoginActivity.EXTRA_USERNAME, sessionUsername)
                putExtra(LoginActivity.EXTRA_SESSION_TOKEN, sessionToken)
                putExtra(LoginActivity.EXTRA_GREETING, sessionGreeting)
            })
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        readSessionFromIntent()
    }

    private fun readSessionFromIntent() {
        sessionUsername = intent.getStringExtra(LoginActivity.EXTRA_USERNAME) ?: "Traveller"
        sessionGreeting = intent.getStringExtra(LoginActivity.EXTRA_GREETING) ?: ""
        sessionToken = intent.getStringExtra(LoginActivity.EXTRA_SESSION_TOKEN) ?: ""
        findViewById<TextView>(R.id.tv_hero_subtitle).text =
            sessionGreeting.ifEmpty { "One trip at a time 🏔️" }
    }

    private fun wirePreviewToTrip(
        includeId: Int,
        title: String,
        region: String,
        date: String,
        pricePkr: Int,
        guide: String
    ) {
        findViewById<View>(includeId).setOnClickListener {
            openTripFromMagazine(title, region, date, pricePkr, guide)
        }
    }

    private fun openTripFromMagazine(
        title: String,
        region: String,
        date: String,
        pricePkr: Int,
        guide: String
    ) {
        startActivity(Intent(this, TripInfoActivity::class.java).apply {
            putExtra(TripInfoActivity.EXTRA_TRIP_TITLE, title)
            putExtra(TripInfoActivity.EXTRA_TRIP_REGION, region)
            putExtra(TripInfoActivity.EXTRA_TRIP_DATE, date)
            putExtra(TripInfoActivity.EXTRA_TRIP_PRICE_PKR, pricePkr)
            putExtra(TripInfoActivity.EXTRA_GUIDE_NAME, guide)
            putExtra(LoginActivity.EXTRA_USERNAME, sessionUsername)
            putExtra(LoginActivity.EXTRA_SESSION_TOKEN, sessionToken)
            putExtra(LoginActivity.EXTRA_GREETING, sessionGreeting)
        })
    }

    private fun bindPreviewCard(
        includeId: Int,
        emoji: String,
        name: String,
        region: String,
        category: String,
        rating: Float,
        price: Int
    ) {
        val root = findViewById<View>(includeId)
        root.findViewById<TextView>(R.id.tv_dest_emoji).text = emoji
        root.findViewById<TextView>(R.id.tv_dest_name).text = name
        root.findViewById<TextView>(R.id.tv_dest_region).text = region
        root.findViewById<TextView>(R.id.tv_dest_category).text = category
        root.findViewById<TextView>(R.id.tv_dest_rating).text = "★ $rating"
        root.findViewById<TextView>(R.id.tv_dest_price).text = "PKR $price/night"
    }

    private fun goDashboard(tab: Int) {
        startActivity(Intent(this, DashboardActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(LoginActivity.EXTRA_USERNAME, sessionUsername)
            putExtra(LoginActivity.EXTRA_SESSION_TOKEN, sessionToken)
            putExtra(LoginActivity.EXTRA_GREETING, sessionGreeting)
            putExtra(DashboardActivity.EXTRA_INITIAL_TAB, tab)
        })
    }

    private fun setupMainBottomNav() {
        findViewById<LinearLayout>(R.id.nav_explore).setOnClickListener {
            goDashboard(DashboardActivity.TAB_EXPLORE)
        }
        findViewById<LinearLayout>(R.id.nav_trips).setOnClickListener {
            goDashboard(DashboardActivity.TAB_TRIPS)
        }
        findViewById<LinearLayout>(R.id.nav_saved).setOnClickListener {
            goDashboard(DashboardActivity.TAB_SAVED)
        }
        findViewById<LinearLayout>(R.id.nav_profile).setOnClickListener {
            goDashboard(DashboardActivity.TAB_PROFILE)
        }
    }
}
