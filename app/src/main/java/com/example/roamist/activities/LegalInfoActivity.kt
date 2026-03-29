package com.example.roamist.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R

class LegalInfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DOCUMENT = "extra_document"
        const val DOC_PRIVACY = "privacy"
        const val DOC_TERMS = "terms"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legal_info)

        setupToolbarBack(findViewById(R.id.legal_toolbar))

        val doc = intent.getStringExtra(EXTRA_DOCUMENT).orEmpty()
        val body = findViewById<TextView>(R.id.tv_legal_body)
        when (doc) {
            DOC_TERMS -> {
                supportActionBar?.title = "Terms of Service"
                body.setText(R.string.legal_terms_body)
            }
            else -> {
                supportActionBar?.title = "Privacy Policy"
                body.setText(R.string.legal_privacy_body)
            }
        }
    }
}
