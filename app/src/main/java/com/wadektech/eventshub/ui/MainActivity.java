package com.wadektech.eventshub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.wadektech.eventshub.R;
import com.wadektech.eventshub.adapter.MainEventsAdapter;
import com.wadektech.eventshub.app.EventsHubApp;
import com.wadektech.eventshub.auth.LoginActivity;
import com.wadektech.eventshub.models.MainEvents;
import com.wadektech.eventshub.repository.EventsHubRepository;
import com.wadektech.eventshub.viewmodels.MainEventsViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainEventsAdapter.OnSingleEventCardClicked {
    public RecyclerView recyclerView;
    public MainEventsAdapter mainEventsAdapter ;
    public LinearLayoutManager layoutManager ;
    public MainEventsViewModel mainEventsViewModel ;
    public List<MainEvents> mainEventsList ;
    ImageButton mMenu , mProfile ;
    public FirebaseAuth mAuth ;
    NiftyDialogBuilder materialDesignAnimatedDialog;
    LinearLayout mProfEvents , mConcerts, mFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventsHubRepository.getInstance().getAllMainEvents();
        EventsHubRepository.getInstance().getAllProfEvents();
        EventsHubRepository.getInstance().getAllSocialEvents();
        EventsHubRepository.getInstance().getAllConcertsAndTheatres();
        EventsHubRepository.getInstance().getAllFriendsEvents();

        materialDesignAnimatedDialog = NiftyDialogBuilder.getInstance(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        recyclerView = findViewById(R.id.rv_event);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(layoutManager);

        mMenu = findViewById(R.id.ib_menu_item);
        mProfile = findViewById(R.id.ib_edit_profile);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mProfEvents = findViewById(R.id.prof_event);
        LinearLayout mLayout = findViewById(R.id.social_events);
        mConcerts = findViewById(R.id.concerts_events);
        mFriends = findViewById(R.id.friends_events);

        mLayout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SocialEventsActivity.class);
            startActivity(intent);
        });

        mConcerts.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ConcertsAndTheatresActivity.class);
            startActivity(intent);
        });

        mMenu.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        mProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            finish();
            startActivity(intent);
        });

        mFriends.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FriendsEventsActivity.class);
            startActivity(intent);
        });

        mainEventsList = new ArrayList<>();

        mainEventsViewModel = ViewModelProviders.of(this).get(MainEventsViewModel.class);
        mainEventsViewModel.getAllEvents().observe(this, mainEventsList -> {
           mainEventsAdapter = new MainEventsAdapter(mainEventsList , MainActivity.this , MainActivity.this::singleEventCardClicked);
           recyclerView.setAdapter(mainEventsAdapter);
        });

        mProfEvents.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfessionaEventsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void singleEventCardClicked(int position) {
        Intent intent = new Intent(MainActivity.this, MoreInfo.class);
        startActivity(intent);
    }

    private void logOutUser() {
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //implement a custom dialog for our logout functionality
    private void animatedDialog() {
        materialDesignAnimatedDialog
                .withTitle("Logout")
                .withMessage("Are you sure you want to log out of Event Hub? Your session will be deleted.")
                .withDialogColor("#1c90ec")
                .withButton1Text("OK")
                .isCancelableOnTouchOutside(true)
                .withButton2Text("Cancel")
                .withDuration(700)
                .withEffect(Effectstype.Fall)
                .setButton1Click(v -> logOutUser())
                .setButton2Click(v -> materialDesignAnimatedDialog.dismiss());
        materialDesignAnimatedDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth != null){

        }
    }
}
