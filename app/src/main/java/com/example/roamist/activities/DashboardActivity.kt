package com.example.roamist.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.roamist.R
import com.example.roamist.fragments.BookingHistoryFragment
import com.example.roamist.fragments.DestinationDetailFragment
import com.example.roamist.fragments.DestinationListFragment
import com.example.roamist.fragments.ExploreGridFragment
import com.example.roamist.fragments.ProfileFragment
import com.example.roamist.models.Destination

/**
 * [F1] Host receives session data from LoginActivity via Intent Extras.
 * [F4] Switches tabs and pushes detail via Fragment Transactions (no Activity restart for detail).
 */
class DashboardActivity : AppCompatActivity(),
    DestinationListFragment.OnDestinationSelectedListener {

    companion object {
        const val EXTRA_INITIAL_TAB = "extra_initial_tab"
        const val TAB_EXPLORE = 0
        const val TAB_TRIPS = 1
        const val TAB_SAVED = 2
        const val TAB_PROFILE = 3
        private const val STATE_TAB = "state_selected_tab"
    }

    private var currentTab = TAB_EXPLORE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME) ?: "Traveller"
        val greeting = intent.getStringExtra(LoginActivity.EXTRA_GREETING) ?: "Explore Pakistan 🌟"
        val sessionToken = intent.getStringExtra(LoginActivity.EXTRA_SESSION_TOKEN) ?: ""

        findViewById<TextView>(R.id.tv_dashboard_greeting).text = greeting
        findViewById<TextView>(R.id.tv_dashboard_username).text =
            "Hi, $username  |  Session: ${sessionToken.take(14)}…"

        findViewById<ImageView>(R.id.btn_dashboard_back).setOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }

        findViewById<ImageView>(R.id.btn_dashboard_notifications).setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java).apply {
                putExtra(LoginActivity.EXTRA_USERNAME, username)
                putExtra(LoginActivity.EXTRA_SESSION_TOKEN, sessionToken)
                putExtra(LoginActivity.EXTRA_GREETING, greeting)
            })
        }

        findViewById<TextView>(R.id.tv_open_magazine).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                putExtra(LoginActivity.EXTRA_USERNAME, username)
                putExtra(LoginActivity.EXTRA_SESSION_TOKEN, sessionToken)
                putExtra(LoginActivity.EXTRA_GREETING, greeting)
            })
        }

        setupBottomNavClicks()

        if (savedInstanceState == null) {
            currentTab = intent.getIntExtra(EXTRA_INITIAL_TAB, TAB_EXPLORE)
            showTab(currentTab, force = true)
        } else {
            currentTab = savedInstanceState.getInt(STATE_TAB, TAB_EXPLORE)
            syncBottomNavUi(currentTab)
        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        val tab = intent.getIntExtra(EXTRA_INITIAL_TAB, currentTab)
        val username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME)
        val greeting = intent.getStringExtra(LoginActivity.EXTRA_GREETING)
        val sessionToken = intent.getStringExtra(LoginActivity.EXTRA_SESSION_TOKEN)
        if (username != null) {
            findViewById<TextView>(R.id.tv_dashboard_greeting).text =
                greeting ?: "Explore Pakistan 🌟"
            findViewById<TextView>(R.id.tv_dashboard_username).text =
                "Hi, $username  |  Session: ${(sessionToken ?: "").take(14)}…"
        }
        popDetailIfNeeded()
        showTab(tab, force = true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_TAB, currentTab)
    }

    private fun setupBottomNavClicks() {
        findViewById<LinearLayout>(R.id.nav_explore).setOnClickListener {
            popDetailIfNeeded()
            showTab(TAB_EXPLORE)
        }
        findViewById<LinearLayout>(R.id.nav_trips).setOnClickListener {
            popDetailIfNeeded()
            showTab(TAB_TRIPS)
        }
        findViewById<LinearLayout>(R.id.nav_saved).setOnClickListener {
            popDetailIfNeeded()
            showTab(TAB_SAVED)
        }
        findViewById<LinearLayout>(R.id.nav_profile).setOnClickListener {
            popDetailIfNeeded()
            showTab(TAB_PROFILE)
        }
    }

    private fun popDetailIfNeeded() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }
    }

    private fun showTab(tab: Int, force: Boolean = false) {
        if (!force && tab == currentTab && supportFragmentManager.backStackEntryCount == 0) return
        currentTab = tab
        syncBottomNavUi(tab)

        val fragment: Fragment = when (tab) {
            TAB_EXPLORE -> DestinationListFragment()
            TAB_TRIPS -> BookingHistoryFragment()
            TAB_SAVED -> ExploreGridFragment()
            TAB_PROFILE -> ProfileFragment()
            else -> DestinationListFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, "tab_$tab")
            .commit()
    }

    private fun syncBottomNavUi(selected: Int) {
        val orange = ContextCompat.getColor(this, R.color.accent_orange)
        val grey = Color.parseColor("#616161")

        val tabs = listOf(
            Triple(R.id.dot_nav_explore, R.id.iv_nav_explore, R.id.tv_nav_explore),
            Triple(R.id.dot_nav_trips, R.id.iv_nav_trips, R.id.tv_nav_trips),
            Triple(R.id.dot_nav_saved, R.id.iv_nav_saved, R.id.tv_nav_saved),
            Triple(R.id.dot_nav_profile, R.id.iv_nav_profile, R.id.tv_nav_profile)
        )

        tabs.forEachIndexed { index, (dotId, ivId, tvId) ->
            val active = index == selected
            findViewById<View>(dotId).visibility = if (active) View.VISIBLE else View.INVISIBLE
            findViewById<ImageView>(ivId).imageTintList = ColorStateList.valueOf(if (active) orange else grey)
            findViewById<TextView>(tvId).apply {
                setTextColor(if (active) orange else grey)
                setTypeface(typeface, if (active) Typeface.BOLD else Typeface.NORMAL)
            }
        }
    }

    override fun onDestinationSelected(destination: Destination) {
        val detailFragment = DestinationDetailFragment.newInstance(destination)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right
            )
            .replace(R.id.fragment_container, detailFragment, "detail_fragment")
            .addToBackStack("detail")
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
