package com.wadektech.eventshub.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.adapter.ProfessionalEventsAdapter
import com.wadektech.eventshub.adapter.ProfessionalEventsAdapter.OnSingleProfEventClicked
import com.wadektech.eventshub.database.MainEventsRoomDatabase
import com.wadektech.eventshub.models.ProfessionalEvents
import com.wadektech.eventshub.repository.EventsHubRepository
import com.wadektech.eventshub.utils.EventsHubViewModelFactory
import com.wadektech.eventshub.viewmodels.EventsHubViewModel
import java.util.*

class ProfessionaEventsActivity : AppCompatActivity(), OnSingleProfEventClicked {
    private lateinit var mRecycler: RecyclerView
    private lateinit var mLayout: LinearLayoutManager
    private lateinit var mProfAdapter: ProfessionalEventsAdapter
    private lateinit var eventsHubViewModel: EventsHubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professiona_events)

        mRecycler = findViewById(R.id.rec_prof_events)

        initRecyclerview()

        mProfAdapter = ProfessionalEventsAdapter(this)
        mRecycler.adapter = mProfAdapter

        val db = MainEventsRoomDatabase(this)
        val repo = EventsHubRepository(db)
        val factory = EventsHubViewModelFactory(repo)

        eventsHubViewModel = ViewModelProvider(this, factory).get(EventsHubViewModel::class.java)
        eventsHubViewModel.getAllProfEvents.observe(this, Observer {
            mProfAdapter.submitList(it)
        })
    }

    override fun singleProfessionalEventClicked(position: Int) {
        val intent = Intent(this@ProfessionaEventsActivity, MoreInfo::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@ProfessionaEventsActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerview(){
        mRecycler.setHasFixedSize(true)
        mLayout = LinearLayoutManager(this)
        mRecycler.layoutManager = mLayout
    }
}