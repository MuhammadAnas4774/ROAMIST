package com.example.roamist.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.roamist.R
import com.example.roamist.activities.MapsActivity
import com.example.roamist.activities.forwardSession

/**
 * Inflates fragment_explore_grid.xml — region shortcuts that open the map screen with Intent Extras.
 */
class ExploreGridFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_explore_grid, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRegionCard(view, R.id.card_punjab, "Punjab")
        bindRegionCard(view, R.id.card_sindh, "Sindh")
        bindRegionCard(view, R.id.card_kpk, "Khyber Pakhtunkhwa")
        bindRegionCard(view, R.id.card_balochistan, "Balochistan")
        bindRegionCard(view, R.id.card_gilgit, "Gilgit-Baltistan")
        bindRegionCard(view, R.id.card_ajk, "Azad Kashmir")
    }

    private fun bindRegionCard(root: View, cardId: Int, region: String) {
        root.findViewById<CardView>(cardId).setOnClickListener {
            startActivity(Intent(requireContext(), MapsActivity::class.java).apply {
                putExtra(MapsActivity.EXTRA_REGION, region)
                forwardSession(requireActivity())
            })
        }
    }
}
