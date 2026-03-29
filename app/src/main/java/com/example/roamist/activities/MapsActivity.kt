package com.example.roamist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.roamist.R

/** activity_map_view.xml — decorative map explorer; region name via Intent Extra. */
class MapsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REGION = "extra_region"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

        val region = intent.getStringExtra(EXTRA_REGION).orEmpty()
        val toolbar = findViewById<Toolbar>(R.id.map_toolbar)
        setupToolbarBack(toolbar)
        supportActionBar?.title = if (region.isNotEmpty()) "Map — $region" else "Map Explorer"

        findViewById<Button>(R.id.btn_notify_me).setOnClickListener {
            startActivity(Intent(this, NotifyAckActivity::class.java).apply {
                putExtra(EXTRA_REGION, region)
                forwardSession(this@MapsActivity)
            })
        }

        findViewById<TextView>(R.id.tv_go_explore).setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                putExtra(DashboardActivity.EXTRA_INITIAL_TAB, DashboardActivity.TAB_EXPLORE)
                forwardSession(this@MapsActivity)
            })
            finish()
        }
    }
}
