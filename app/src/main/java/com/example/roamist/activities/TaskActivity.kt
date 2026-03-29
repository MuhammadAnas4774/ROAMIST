package com.example.roamist.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roamist.R

/** activity_task.xml — checklist opened from detail “Plan my trip”. */
class TaskActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TRIP_NAME = "extra_trip_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        setupToolbarBack(findViewById(R.id.task_toolbar), getString(R.string.trip_checklist_title))

        val trip = intent.getStringExtra(EXTRA_TRIP_NAME) ?: "Your trip"
        findViewById<TextView>(R.id.tv_task_context).text = getString(R.string.trip_planning_label, trip)

        findViewById<Button>(R.id.btn_task_done).setOnClickListener { finish() }
    }
}
