package com.wadektech.eventshub.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.adapter.ConcertsAndTheatreAdapter
import com.wadektech.eventshub.adapter.ConcertsAndTheatreAdapter.OnSingleConcertItemClicked
import com.wadektech.eventshub.database.MainEventsRoomDatabase
import com.wadektech.eventshub.databinding.ActivityConcertsAndTheatresBinding
import com.wadektech.eventshub.repository.EventsHubRepository
import com.wadektech.eventshub.utils.EventsHubViewModelFactory
import com.wadektech.eventshub.viewmodels.EventsHubViewModel


class ConcertsAndTheatresActivity : AppCompatActivity(), OnSingleConcertItemClicked {
    private lateinit var binding: ActivityConcertsAndTheatresBinding
    //private lateinit var eventsHubViewModel : EventsHubViewModel
    val db = MainEventsRoomDatabase(this)
    val repo = EventsHubRepository(db)
    val factory = EventsHubViewModelFactory(repo)

    private val eventsHubViewModel : EventsHubViewModel by lazy {
        ViewModelProvider(this, factory).get(EventsHubViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_concerts_and_theatres)

        binding.lifecycleOwner = this

        binding.viewModel = eventsHubViewModel

        binding.rvConcerts.adapter = ConcertsAndTheatreAdapter(this)

    }

    override fun onSingleConcertItemClicked(position: Int) {
        val intent = Intent(this@ConcertsAndTheatresActivity, MoreInfo::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "ConcertsAndTheatresActivity"
    }
}