package com.wadektech.eventshub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.wadektech.eventshub.R;
import com.wadektech.eventshub.adapter.FriendsEventsAdapter;
import com.wadektech.eventshub.models.FriendsEvents;
import com.wadektech.eventshub.viewmodels.FriendsEventsViewModel;

import java.util.ArrayList;
import java.util.List;

public class FriendsEventsActivity extends AppCompatActivity implements FriendsEventsAdapter.OnSingleFriendsEventItemClicked {
    RecyclerView mRecycler;
    LinearLayoutManager layoutManager;
    FriendsEventsAdapter fAdapter ;
    List<FriendsEvents> friendsEventsList ;
    FriendsEventsViewModel friendsEventsViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_events);

        mRecycler = findViewById(R.id.rv_friends);
        layoutManager =  new LinearLayoutManager(this);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(layoutManager);

        friendsEventsList = new ArrayList<>() ;

        friendsEventsViewModel = ViewModelProviders.of(this).get(FriendsEventsViewModel.class);
        friendsEventsViewModel.getAllFriendsEvents().observe(this, new Observer<List<FriendsEvents>>() {
            @Override
            public void onChanged(List<FriendsEvents> friendsEventsList) {
                fAdapter = new FriendsEventsAdapter(friendsEventsList, FriendsEventsActivity.this, FriendsEventsActivity.this::singleFriendsEventsItemClicked);
                mRecycler.setAdapter(fAdapter);
            }
        });
    }

    @Override
    public void singleFriendsEventsItemClicked(int position) {
        Intent intent = new Intent(FriendsEventsActivity.this, MoreInfo.class);
        startActivity(intent);
    }
}
