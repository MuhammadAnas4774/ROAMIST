package com.example.roamist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.roamist.R
import com.example.roamist.activities.LoginActivity
import com.example.roamist.activities.TaskActivity
import com.example.roamist.activities.forwardSession
import com.example.roamist.models.Destination
import android.content.Intent

/**
 * [F2] Receives a Destination object via a Bundle (Parcelable).
 *       Data is never held in a global/static variable.
 * [F4] This fragment is loaded via a Fragment Transaction from DashboardActivity.
 */
class DestinationDetailFragment : Fragment() {

    companion object {
        private const val ARG_DESTINATION = "arg_destination"

        /**
         * [F2] Factory method — the ONLY way to construct this fragment.
         * Packages the Destination Parcelable into a Bundle and attaches it
         * to fragment.arguments. Zero global/static state is used.
         */
        fun newInstance(destination: Destination): DestinationDetailFragment {
            val fragment = DestinationDetailFragment()
            val bundle = Bundle().apply {
                putParcelable(ARG_DESTINATION, destination)   // [F2] Bundle ← Parcelable
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_destination_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // [F2] Extract the Parcelable Destination from the Bundle
        @Suppress("DEPRECATION")
        val destination: Destination? = arguments?.getParcelable(ARG_DESTINATION)
        destination?.let { bindDestination(view, it) }
    }

    /** Bind all destination fields to the corresponding TextViews */
    private fun bindDestination(view: View, dest: Destination) {
        view.findViewById<TextView>(R.id.tv_detail_emoji).text           = dest.iconEmoji
        view.findViewById<TextView>(R.id.tv_detail_name).text            = dest.name
        view.findViewById<TextView>(R.id.tv_detail_region).text          = "📍 ${dest.region}"
        view.findViewById<TextView>(R.id.tv_detail_category_badge).text  = dest.category
        view.findViewById<TextView>(R.id.tv_detail_rating).text          = "★ ${dest.rating} / 5.0"
        view.findViewById<TextView>(R.id.tv_detail_price).text           = "PKR ${dest.pricePerNight} / night"
        view.findViewById<TextView>(R.id.tv_detail_description).text     = dest.description

        view.findViewById<Button>(R.id.btn_plan_trip).setOnClickListener {
            val username = activity?.intent?.getStringExtra(LoginActivity.EXTRA_USERNAME).orEmpty()
            requireActivity().startActivity(Intent(requireContext(), TaskActivity::class.java).apply {
                putExtra(TaskActivity.EXTRA_TRIP_NAME, dest.name)
                putExtra(LoginActivity.EXTRA_USERNAME, username)
                forwardSession(requireActivity())
            })
        }
    }
}
