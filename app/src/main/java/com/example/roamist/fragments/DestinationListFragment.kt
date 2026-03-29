package com.example.roamist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roamist.R
import com.example.roamist.adapters.DestinationAdapter
import com.example.roamist.models.Destination

/**
 * [F3] Hosts the RecyclerView list of Pakistan travel destinations.
 * [F4] Communicates back to DashboardActivity via the OnDestinationSelectedListener
 *       interface to trigger a Fragment Transaction — no static/global access.
 * [F5] Wires the search EditText to the adapter's Filterable implementation.
 */
class DestinationListFragment : Fragment() {

    /** [F4] Callback interface — must be implemented by the host Activity */
    interface OnDestinationSelectedListener {
        fun onDestinationSelected(destination: Destination)
    }

    private lateinit var adapter: DestinationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_destination_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_destinations)
        val etSearch: EditText         = view.findViewById(R.id.et_search_destinations)

        // [F3] Initialise the custom adapter with seed data
        adapter = DestinationAdapter(buildDestinationData()) { destination ->
            // [F4] Delegate navigation to the host Activity — never access Activity statically
            (activity as? OnDestinationSelectedListener)?.onDestinationSelected(destination)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // [F5] Every keystroke fires adapter.filter.filter(), updating the list in real-time
        etSearch.addTextChangedListener { text ->
            adapter.filter.filter(text)
        }
    }

    /** Seed data — 12 real Pakistani destinations */
    private fun buildDestinationData(): MutableList<Destination> = mutableListOf(
        Destination(
            1, "Hunza Valley", "Gilgit-Baltistan",
            "A breathtaking valley surrounded by towering peaks of the Karakoram Range. " +
            "Famous for its apricot blossoms, ancient forts, and crystal-clear glacial rivers.",
            4.9f, 8500, "Mountains", "🏔️"
        ),
        Destination(
            2, "Lahore Old City", "Punjab",
            "The cultural heart of Pakistan, home to the majestic Badshahi Mosque, Lahore Fort, " +
            "and Anarkali Bazaar. A living museum of Mughal architecture.",
            4.7f, 5500, "Historical", "🕌"
        ),
        Destination(
            3, "Fairy Meadows", "Gilgit-Baltistan",
            "A scenic alpine meadow at the base of Nanga Parbat, the world's 9th highest peak. " +
            "Accessible by jeep track and trekking trails.",
            4.8f, 6000, "Mountains", "🌿"
        ),
        Destination(
            4, "Karachi Beach", "Sindh",
            "Pakistan's coastline gateway — visit French Beach, Clifton Beach, and Hawks Bay " +
            "for a coastal escape from the city bustle.",
            4.2f, 4200, "Coastal", "🏖️"
        ),
        Destination(
            5, "Skardu", "Gilgit-Baltistan",
            "Gateway to K2 and the Baltoro Glacier. Skardu offers stunning landscapes, " +
            "Shangrila Resort, and the serene Satpara Lake.",
            4.8f, 7200, "Mountains", "🏕️"
        ),
        Destination(
            6, "Mohenjo-daro", "Sindh",
            "One of the world's earliest urban settlements, dating back to 2500 BCE — " +
            "a UNESCO World Heritage Site and archaeological marvel.",
            4.5f, 3500, "Historical", "🏛️"
        ),
        Destination(
            7, "Nathia Gali", "Khyber Pakhtunkhwa",
            "A hill station in the Galyat region, perfect for trekking through pine forests " +
            "and enjoying cool summers at high altitude.",
            4.6f, 5800, "Hill Station", "🌲"
        ),
        Destination(
            8, "Deosai Plains", "Gilgit-Baltistan",
            "One of the world's highest plateaus at 4,114 m, home to the Himalayan brown bear " +
            "and a carpet of wildflowers in summer.",
            4.7f, 5000, "Wildlife", "🦁"
        ),
        Destination(
            9, "Swat Valley", "Khyber Pakhtunkhwa",
            "Known as the 'Switzerland of Pakistan' — lush green mountains, emerald rivers, " +
            "and ancient Buddhist ruins make it unforgettable.",
            4.6f, 6500, "Valley", "🏞️"
        ),
        Destination(
            10, "Rohtas Fort", "Punjab",
            "A 16th-century fortress built by Sher Shah Suri to control the Punjab region. " +
            "A UNESCO World Heritage Site with massive stone walls.",
            4.4f, 2500, "Historical", "🏰"
        ),
        Destination(
            11, "Gwadar Port", "Balochistan",
            "An emerging deep-sea port city with pristine beaches, Hammerhead Rock, and fresh " +
            "seafood — part of the CPEC megaproject.",
            4.3f, 3800, "Coastal", "⚓"
        ),
        Destination(
            12, "Kalash Valley", "Khyber Pakhtunkhwa",
            "Home to the unique Kalash people with their distinct culture, festivals, and " +
            "colorful attire. A truly rare anthropological gem.",
            4.7f, 5200, "Cultural", "🎭"
        )
    )
}
