package com.example.roamist.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.roamist.R
import com.example.roamist.activities.CancelBookingActivity
import com.example.roamist.activities.CheckoutActivity
import com.example.roamist.activities.TripInfoActivity
import com.example.roamist.activities.TripReviewActivity
import com.example.roamist.activities.forwardSession

/**
 * Hosts activity_booking_history.xml; every card and action button routes via Intent Extras.
 */
class BookingHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.activity_booking_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wireCard1(view)
        wireCard2(view)
        wireCard3(view)
        wireCard4(view)
        wireCard5(view)
    }

    private fun parsePkr(text: String): Int =
        text.filter { it.isDigit() }.toIntOrNull() ?: 0

    private fun tripIntent(
        root: View,
        destId: Int,
        regionId: Int,
        dateId: Int,
        durationId: Int,
        amountId: Int,
        guideId: Int
    ): Intent {
        val title = root.findViewById<TextView>(destId).text.toString()
        val region = root.findViewById<TextView>(regionId).text.toString()
        val date = root.findViewById<TextView>(dateId).text.toString()
        val duration = root.findViewById<TextView>(durationId).text.toString()
        val amountText = root.findViewById<TextView>(amountId).text.toString()
        val guide = root.findViewById<TextView>(guideId).text.toString()
        return Intent(requireContext(), TripInfoActivity::class.java).apply {
            putExtra(TripInfoActivity.EXTRA_TRIP_TITLE, title)
            putExtra(TripInfoActivity.EXTRA_TRIP_DATE, date)
            putExtra(TripInfoActivity.EXTRA_TRIP_REGION, region)
            putExtra(TripInfoActivity.EXTRA_DURATION, duration)
            putExtra(TripInfoActivity.EXTRA_TRIP_PRICE_PKR, parsePkr(amountText))
            putExtra(TripInfoActivity.EXTRA_GUIDE_NAME, guide)
            forwardSession(requireActivity())
        }
    }

    private fun checkoutFromCard(
        root: View,
        destId: Int,
        regionId: Int,
        dateId: Int,
        durationId: Int,
        amountId: Int,
        guideId: Int
    ) {
        val base = Intent(requireContext(), CheckoutActivity::class.java)
        val ti = tripIntent(root, destId, regionId, dateId, durationId, amountId, guideId)
        base.putExtras(ti.extras ?: Bundle())
        base.forwardSession(requireActivity())
        startActivity(base)
    }

    private fun reviewFromTitle(root: View, destId: Int) {
        val title = root.findViewById<TextView>(destId).text.toString()
        startActivity(Intent(requireContext(), TripReviewActivity::class.java).apply {
            putExtra(TripInfoActivity.EXTRA_TRIP_TITLE, title)
            forwardSession(requireActivity())
        })
    }

    private fun cancelFromTitle(root: View, destId: Int) {
        val title = root.findViewById<TextView>(destId).text.toString()
        startActivity(Intent(requireContext(), CancelBookingActivity::class.java).apply {
            putExtra(TripInfoActivity.EXTRA_TRIP_TITLE, title)
            forwardSession(requireActivity())
        })
    }

    private fun wireCard1(root: View) {
        root.findViewById<CardView>(R.id.card_booking_1).setOnClickListener {
            startActivity(tripIntent(root, R.id.b1_destination, R.id.b1_region, R.id.b1_date, R.id.b1_duration, R.id.b1_amount, R.id.b1_guide))
        }
        root.findViewById<Button>(R.id.btn_b1_review).setOnClickListener {
            reviewFromTitle(root, R.id.b1_destination)
        }
        root.findViewById<Button>(R.id.btn_b1_rebook).setOnClickListener {
            checkoutFromCard(root, R.id.b1_destination, R.id.b1_region, R.id.b1_date, R.id.b1_duration, R.id.b1_amount, R.id.b1_guide)
        }
    }

    private fun wireCard2(root: View) {
        root.findViewById<CardView>(R.id.card_booking_2).setOnClickListener {
            startActivity(tripIntent(root, R.id.b2_destination, R.id.b2_region, R.id.b2_date, R.id.b2_duration, R.id.b2_amount, R.id.b2_guide))
        }
        root.findViewById<Button>(R.id.btn_b2_cancel).setOnClickListener {
            cancelFromTitle(root, R.id.b2_destination)
        }
        root.findViewById<Button>(R.id.btn_b2_details).setOnClickListener {
            startActivity(tripIntent(root, R.id.b2_destination, R.id.b2_region, R.id.b2_date, R.id.b2_duration, R.id.b2_amount, R.id.b2_guide))
        }
    }

    private fun wireCard3(root: View) {
        root.findViewById<CardView>(R.id.card_booking_3).setOnClickListener {
            startActivity(tripIntent(root, R.id.b3_destination, R.id.b3_region, R.id.b3_date, R.id.b3_duration, R.id.b3_amount, R.id.b3_guide))
        }
        root.findViewById<Button>(R.id.btn_b3_review).setOnClickListener {
            reviewFromTitle(root, R.id.b3_destination)
        }
        root.findViewById<Button>(R.id.btn_b3_rebook).setOnClickListener {
            checkoutFromCard(root, R.id.b3_destination, R.id.b3_region, R.id.b3_date, R.id.b3_duration, R.id.b3_amount, R.id.b3_guide)
        }
    }

    private fun wireCard4(root: View) {
        root.findViewById<CardView>(R.id.card_booking_4).setOnClickListener {
            startActivity(tripIntent(root, R.id.b4_destination, R.id.b4_region, R.id.b4_date, R.id.b4_duration, R.id.b4_amount, R.id.b4_guide))
        }
        root.findViewById<Button>(R.id.btn_b4_cancel).setOnClickListener {
            cancelFromTitle(root, R.id.b4_destination)
        }
        root.findViewById<Button>(R.id.btn_b4_details).setOnClickListener {
            startActivity(tripIntent(root, R.id.b4_destination, R.id.b4_region, R.id.b4_date, R.id.b4_duration, R.id.b4_amount, R.id.b4_guide))
        }
    }

    private fun wireCard5(root: View) {
        root.findViewById<CardView>(R.id.card_booking_5).setOnClickListener {
            startActivity(tripIntent(root, R.id.b5_destination, R.id.b5_region, R.id.b5_date, R.id.b5_duration, R.id.b5_amount, R.id.b5_guide))
        }
        root.findViewById<Button>(R.id.btn_b5_review).setOnClickListener {
            reviewFromTitle(root, R.id.b5_destination)
        }
        root.findViewById<Button>(R.id.btn_b5_rebook).setOnClickListener {
            checkoutFromCard(root, R.id.b5_destination, R.id.b5_region, R.id.b5_date, R.id.b5_duration, R.id.b5_amount, R.id.b5_guide)
        }
    }
}
