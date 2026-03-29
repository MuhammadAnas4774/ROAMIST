package com.example.roamist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R
import java.util.Locale

class CheckoutActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SERVICE_FEE_PKR = "checkout_service_fee"
        private const val DEFAULT_FEE = 2500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        setupToolbarBack(findViewById(R.id.checkout_toolbar))

        val title = intent.getStringExtra(TripInfoActivity.EXTRA_TRIP_TITLE).orEmpty()
        val region = intent.getStringExtra(TripInfoActivity.EXTRA_TRIP_REGION).orEmpty()
        val date = intent.getStringExtra(TripInfoActivity.EXTRA_TRIP_DATE).orEmpty()
        val guide = intent.getStringExtra(TripInfoActivity.EXTRA_GUIDE_NAME).orEmpty()
        val basePrice = intent.getIntExtra(TripInfoActivity.EXTRA_TRIP_PRICE_PKR, 45_000)
        val fee = intent.getIntExtra(EXTRA_SERVICE_FEE_PKR, DEFAULT_FEE)
        val total = basePrice + fee

        findViewById<TextView>(R.id.tv_checkout_destination).text = title.ifEmpty { "Your trip" }
        findViewById<TextView>(R.id.tv_checkout_region).text = region.ifEmpty { "Pakistan" }
        findViewById<TextView>(R.id.tv_checkout_date).text = date.ifEmpty { "TBC" }
        findViewById<TextView>(R.id.tv_checkout_guide).text = guide.ifEmpty { "Assigned on departure" }
        findViewById<TextView>(R.id.tv_checkout_total).text =
            String.format(Locale.getDefault(), "PKR %,d", total).replace(',', '.')

        findViewById<Button>(R.id.btn_complete_checkout).setOnClickListener {
            val methodLabel = when (findViewById<RadioGroup>(R.id.rg_payment).checkedRadioButtonId) {
                R.id.rb_jazzcash -> "JazzCash / Easypaisa"
                R.id.rb_card -> "Debit / Credit card"
                R.id.rb_bank -> "Bank transfer"
                else -> "JazzCash / Easypaisa"
            }
            startActivity(Intent(this, TripSuccessActivity::class.java).apply {
                putExtra(TripSuccessActivity.EXTRA_SCREEN_MODE, TripSuccessActivity.MODE_PAID)
                putExtra(TripInfoActivity.EXTRA_TRIP_TITLE, title)
                putExtra(TripInfoActivity.EXTRA_TRIP_DATE, date)
                putExtra(CheckoutFlowExtras.EXTRA_PAYMENT_METHOD, methodLabel)
                forwardSession(this@CheckoutActivity)
            })
            finish()
        }
    }
}

object CheckoutFlowExtras {
    const val EXTRA_PAYMENT_METHOD = "extra_payment_method"
}
