package com.wadektech.eventshub.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.adapter.FriendsEventsAdapter
import com.wadektech.eventshub.adapter.FriendsEventsAdapter.OnSingleFriendsEventItemClicked
import com.wadektech.eventshub.viewmodels.EventsHubViewModel

class FriendsEventsActivity : AppCompatActivity(), OnSingleFriendsEventItemClicked {
    private lateinit var mRecycler: RecyclerView
    private lateinit var mLayout: LinearLayoutManager
    private lateinit var fAdapter: FriendsEventsAdapter
    private lateinit var eventsHubViewModel: EventsHubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_events)
        mRecycler = findViewById(R.id.rv_friends)

        initRecyclerview()

        eventsHubViewModel = ViewModelProviders.of(this).get(EventsHubViewModel::class.java)
        eventsHubViewModel.getAllFriendsEvents().observe(this, Observer {
            fAdapter.submitList(it)
        })
    }

    override fun singleFriendsEventsItemClicked(position: Int) {
        val intent = Intent(this@FriendsEventsActivity, MoreInfo::class.java)
        startActivity(intent)
    }

    private fun initRecyclerview(){
        mRecycler.setHasFixedSize(true)
        mLayout = LinearLayoutManager(this)
        mRecycler.layoutManager = mLayout
        fAdapter = FriendsEventsAdapter(this)
        mRecycler.adapter = fAdapter
    }
}