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
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.example.roamist.activities.AppSettingsActivity
import com.example.roamist.activities.ChangePasswordActivity
import com.example.roamist.activities.EditProfileActivity
import com.example.roamist.activities.LegalInfoActivity
import com.example.roamist.activities.LoginActivity
import com.example.roamist.activities.MapsActivity
import com.example.roamist.activities.NotificationsActivity
import com.example.roamist.activities.forwardSession

/**
 * Inflates activity_profile.xml; opens other screens only via Intent Extras (no SharedPreferences).
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.activity_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = requireActivity().intent.getStringExtra(LoginActivity.EXTRA_USERNAME) ?: "Traveller"
        val session = requireActivity().intent.getStringExtra(LoginActivity.EXTRA_SESSION_TOKEN) ?: ""

        view.findViewById<TextView>(R.id.tv_user_name).text = username
        view.findViewById<TextView>(R.id.tv_user_email).text =
            "${username.lowercase().replace(' ', '.')}@roamist.app"

        val phone = view.findViewById<TextView>(R.id.info_value_phone).text.toString()
        val city = view.findViewById<TextView>(R.id.info_value_city).text.toString()

        view.findViewById<TextView>(R.id.tv_edit_personal).setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java).apply {
                putExtra(EditProfileActivity.EXTRA_FULL_NAME, username)
                putExtra(EditProfileActivity.EXTRA_PHONE, phone)
                putExtra(EditProfileActivity.EXTRA_CITY, city)
                forwardSession(requireActivity())
            })
        }

        view.findViewById<View>(R.id.row_change_password).setOnClickListener {
            startActivity(Intent(requireContext(), ChangePasswordActivity::class.java).apply {
                forwardSession(requireActivity())
            })
        }

        view.findViewById<View>(R.id.row_privacy).setOnClickListener {
            startActivity(Intent(requireContext(), LegalInfoActivity::class.java).apply {
                putExtra(LegalInfoActivity.EXTRA_DOCUMENT, LegalInfoActivity.DOC_PRIVACY)
                forwardSession(requireActivity())
            })
        }

        view.findViewById<View>(R.id.row_gps).setOnClickListener {
            startActivity(Intent(requireContext(), MapsActivity::class.java).apply {
                putExtra(MapsActivity.EXTRA_REGION, "GPS preferences")
                forwardSession(requireActivity())
            })
        }

        view.findViewById<SwitchCompat>(R.id.switch_gps).setOnCheckedChangeListener { _, on ->
            Toast.makeText(requireContext(), "GPS tracking ${if (on) "enabled" else "paused"} (demo)", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<View>(R.id.row_notifications).setOnClickListener {
            startActivity(Intent(requireContext(), NotificationsActivity::class.java).apply {
                forwardSession(requireActivity())
            })
        }

        view.findViewById<CardView>(R.id.card_account_settings).setOnClickListener {
            startActivity(Intent(requireContext(), AppSettingsActivity::class.java).apply {
                forwardSession(requireActivity())
            })
        }

        view.findViewById<Button>(R.id.btn_logout).setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            requireActivity().finish()
        }
    }
}
