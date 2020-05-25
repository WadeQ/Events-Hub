package com.wadektech.eventshub.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.wadektech.eventshub.R
import com.wadektech.eventshub.adapter.MainEventsAdapter
import com.wadektech.eventshub.adapter.MainEventsAdapter.OnSingleEventCardClicked
import com.wadektech.eventshub.auth.LoginActivity
import com.wadektech.eventshub.database.MainEventsRoomDatabase
import com.wadektech.eventshub.models.MainEvents
import com.wadektech.eventshub.repository.EventsHubRepository
import com.wadektech.eventshub.ui.ConcertsAndTheatresActivity
import com.wadektech.eventshub.utils.EventsHubViewModelFactory
import com.wadektech.eventshub.viewmodels.EventsHubViewModel
import java.util.*

class MainActivity : AppCompatActivity(), OnSingleEventCardClicked {
    private lateinit var mRecycler: RecyclerView
    private lateinit var mainEventsAdapter: MainEventsAdapter
    private lateinit var mLayout: LinearLayoutManager
    private lateinit var eventsHubViewModel: EventsHubViewModel
    private lateinit var mMenu: ImageButton
    private lateinit var mProfile: ImageButton
    private lateinit var mAuth: FirebaseAuth
    private lateinit var materialDesignAnimatedDialog: NiftyDialogBuilder
    private lateinit var mProfEvents: LinearLayout
    private lateinit var mConcerts: LinearLayout
    private lateinit var mFriends: LinearLayout

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecycler = findViewById(R.id.rv_event)
        mMenu = findViewById(R.id.ib_menu_item)
        mProfile = findViewById(R.id.ib_edit_profile)
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        mProfEvents = findViewById(R.id.prof_event)
        val layout = findViewById<LinearLayout>(R.id.social_events)
        mConcerts = findViewById(R.id.concerts_events)
        mFriends = findViewById(R.id.friends_events)

        val db = MainEventsRoomDatabase(this)
        val repo = EventsHubRepository(db)
        val factory = EventsHubViewModelFactory(repo)

        eventsHubViewModel = EventsHubViewModel(repo)

        eventsHubViewModel.allMainEventsRoom()
        eventsHubViewModel.allProfEvents()
        eventsHubViewModel.allSocialEvents()
        eventsHubViewModel.allConcertsAndTheatres()
        eventsHubViewModel.allFriendEventsRoom()

        initRecyclerview()

        materialDesignAnimatedDialog = NiftyDialogBuilder.getInstance(this)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        layout.setOnClickListener {
            val intent = Intent(this@MainActivity, SocialEventsActivity::class.java)
            startActivity(intent)
        }
        mConcerts.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, ConcertsAndTheatresActivity::class.java)
            startActivity(intent)
        })
        mMenu.setOnClickListener(View.OnClickListener {
            mAuth.signOut()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        })
        mProfile.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            finish()
            startActivity(intent)
        })
        mFriends.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, FriendsEventsActivity::class.java)
            startActivity(intent)
        })

       eventsHubViewModel = ViewModelProvider(this,factory).get(EventsHubViewModel::class.java)
        eventsHubViewModel.getAllMainEvents().observe(this, androidx.lifecycle.Observer {
            mainEventsAdapter.submitList(it)
        })

        mProfEvents.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, ProfessionaEventsActivity::class.java)
            startActivity(intent)
        })
    }

    override fun singleEventCardClicked(position: Int) {
        val intent = Intent(this@MainActivity, MoreInfo::class.java)
        startActivity(intent)
    }

    private fun logOutUser() {
        mAuth.signOut()
        val intent = Intent(applicationContext, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    //implement a custom dialog for our logout functionality
    private fun animatedDialog() {
        materialDesignAnimatedDialog
                .withTitle("Logout")
                .withMessage("Are you sure you want to log out of Event Hub? Your session will be deleted.")
                .withDialogColor("#1c90ec")
                .withButton1Text("OK")
                .isCancelableOnTouchOutside(true)
                .withButton2Text("Cancel")
                .withDuration(700)
                .withEffect(Effectstype.Fall)
                .setButton1Click { v: View? -> logOutUser() }
                .setButton2Click { v: View? -> materialDesignAnimatedDialog!!.dismiss() }
        materialDesignAnimatedDialog!!.show()
    }

    override fun onStart() {
        super.onStart()

    }

    private fun initRecyclerview(){
        mRecycler.setHasFixedSize(true)
        mLayout = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true)
        mRecycler.layoutManager = mLayout
        mainEventsAdapter = MainEventsAdapter(this)
        mRecycler.adapter = mainEventsAdapter
    }
}