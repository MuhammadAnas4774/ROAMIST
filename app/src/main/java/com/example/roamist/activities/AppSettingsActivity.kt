package com.example.roamist.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R

/** Wires every control in activity_app_settings.xml. */
class AppSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_settings)

        setupToolbarBack(findViewById(R.id.settings_toolbar))

        findViewById<android.view.View>(R.id.row_change_password).setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java).apply { forwardSession(this@AppSettingsActivity) })
        }
        findViewById<android.view.View>(R.id.row_privacy_policy).setOnClickListener {
            startActivity(Intent(this, LegalInfoActivity::class.java).apply {
                putExtra(LegalInfoActivity.EXTRA_DOCUMENT, LegalInfoActivity.DOC_PRIVACY)
                forwardSession(this@AppSettingsActivity)
            })
        }
        findViewById<android.view.View>(R.id.row_rate_app).setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
            } catch (_: Exception) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }
        findViewById<android.view.View>(R.id.row_contact_support).setOnClickListener {
            val i = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:support@roamist.app?subject=" + Uri.encode("Roamist support"))
            }
            try {
                startActivity(i)
            } catch (_: Exception) {
                Toast.makeText(this, "support@roamist.app", Toast.LENGTH_LONG).show()
            }
        }

        findViewById<Button>(R.id.btn_sign_out_settings).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            finish()
        }

        toastOnToggle(R.id.switch_trip_alerts, "Trip alerts")
        toastOnToggle(R.id.switch_deals, "Deal alerts")
        toastOnToggle(R.id.switch_weather_warnings, "Weather warnings")
        toastOnToggle(R.id.switch_gps, "Precise location")
        toastOnToggle(R.id.switch_offline_maps, "Offline maps")
        findViewById<Switch>(R.id.switch_2fa).setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "2FA will apply on next sign-in (demo)" else "2FA off (demo)", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toastOnToggle(id: Int, label: String) {
        findViewById<Switch>(id).setOnCheckedChangeListener { _, on ->
            Toast.makeText(this, "$label ${if (on) "on" else "off"}", Toast.LENGTH_SHORT).show()
        }
    }
}
