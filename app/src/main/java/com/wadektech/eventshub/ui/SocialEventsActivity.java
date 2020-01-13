package com.wadektech.eventshub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.wadektech.eventshub.R;
import com.wadektech.eventshub.adapter.SocialEventsAdapter;
import com.wadektech.eventshub.models.SocialEvents;
import com.wadektech.eventshub.viewmodels.SocialEventsViewModel;

import java.util.ArrayList;
import java.util.List;

public class SocialEventsActivity extends AppCompatActivity implements SocialEventsAdapter.OnSingleSocialEventClicked {
    RecyclerView mRecycler;
    SocialEventsAdapter socialEventsAdapter ;
    LinearLayoutManager layoutManager;
    List<SocialEvents> socialEventsList;
    SocialEventsViewModel socialEventsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_events);

        mRecycler = findViewById(R.id.rv_social_events);
        layoutManager = new LinearLayoutManager(this);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(layoutManager);

        socialEventsList = new ArrayList<>();

        socialEventsViewModel = ViewModelProviders.of(this).get(SocialEventsViewModel.class);
        socialEventsViewModel.getAllSocialEvents().observe(this, socialEventsList -> {
            socialEventsAdapter = new SocialEventsAdapter(socialEventsList, SocialEventsActivity.this, SocialEventsActivity.this::onSingleSocialEventItemClicked);
            mRecycler.setAdapter(socialEventsAdapter);
        });
    }

    @Override
    public void onSingleSocialEventItemClicked(int position) {
        Intent intent = new Intent(SocialEventsActivity.this, MoreInfo.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SocialEventsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
