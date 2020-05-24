package com.wadektech.eventshub.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.adapter.SocialEventsAdapter
import com.wadektech.eventshub.adapter.SocialEventsAdapter.OnSingleSocialEventClicked
import com.wadektech.eventshub.viewmodels.EventsHubViewModel
import java.util.*

class SocialEventsActivity : AppCompatActivity(), OnSingleSocialEventClicked {
    private lateinit var mRecycler: RecyclerView
    private lateinit var socialEventsAdapter: SocialEventsAdapter
    private lateinit var mLayout: LinearLayoutManager
    private lateinit var eventsHubViewModel: EventsHubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_events)

        mRecycler = findViewById(R.id.rv_social_events)

        initRecyclerview()

        eventsHubViewModel = ViewModelProviders.of(this).get(EventsHubViewModel::class.java)
        eventsHubViewModel.getAllSocialEvents().observe(this, androidx.lifecycle.Observer {
            socialEventsAdapter.submitList(it)
        })
    }

    override fun onSingleSocialEventItemClicked(position: Int) {
        val intent = Intent(this@SocialEventsActivity, MoreInfo::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@SocialEventsActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerview(){
        mRecycler.setHasFixedSize(true)
        mLayout = LinearLayoutManager(this)
        mRecycler.layoutManager = mLayout
        socialEventsAdapter = SocialEventsAdapter(this)
        mRecycler.adapter = socialEventsAdapter
    }
}