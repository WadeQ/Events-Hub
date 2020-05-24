package com.wadektech.eventshub.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.adapter.ConcertsAndTheatreAdapter
import com.wadektech.eventshub.adapter.ConcertsAndTheatreAdapter.OnSingleConcertItemClicked
import com.wadektech.eventshub.viewmodels.EventsHubViewModel


class ConcertsAndTheatresActivity : AppCompatActivity(), OnSingleConcertItemClicked {
    private lateinit var mRecycler: RecyclerView
    private lateinit var mLayout: LinearLayoutManager
    private lateinit var cAdapter: ConcertsAndTheatreAdapter
    private lateinit var eventsHubViewModel: EventsHubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concerts_and_theatres)

        mRecycler = findViewById(R.id.rv_concerts)

        initRecyclerview()

       eventsHubViewModel = ViewModelProviders.of(this).get(EventsHubViewModel::class.java)
        eventsHubViewModel.getAllConcertsFromRoom().observe(this, Observer {
            cAdapter.submitList(it)
        })
    }

    override fun onSingleConcertItemClicked(position: Int) {
        val intent = Intent(this@ConcertsAndTheatresActivity, MoreInfo::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "ConcertsAndTheatresActivity"
    }

    private fun initRecyclerview(){
        mRecycler.setHasFixedSize(true)
        mLayout = LinearLayoutManager(this)
        mRecycler.layoutManager = mLayout
        cAdapter = ConcertsAndTheatreAdapter(this)
        mRecycler.adapter = cAdapter
    }
}