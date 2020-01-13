package com.wadektech.eventshub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.wadektech.eventshub.R;
import com.wadektech.eventshub.adapter.ConcertsAndTheatreAdapter;
import com.wadektech.eventshub.models.Concerts;
import com.wadektech.eventshub.viewmodels.ConcertsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ConcertsAndTheatresActivity extends AppCompatActivity implements ConcertsAndTheatreAdapter.OnSingleConcertItemClicked {
    public RecyclerView mRecycler ;
    public LinearLayoutManager layoutManager;
    public ConcertsAndTheatreAdapter cAdapter ;
    public List<Concerts> concertsList;
    public ConcertsViewModel concertsViewModel;
    private static final String TAG = "ConcertsAndTheatresActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concerts_and_theatres);

        mRecycler= findViewById(R.id.rv_concerts);
        layoutManager = new LinearLayoutManager(this);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(layoutManager);

        concertsList = new ArrayList<>() ;

        concertsViewModel = ViewModelProviders.of(this).get(ConcertsViewModel.class);
        concertsViewModel.getAllConcertsAndTheatres().observe(this, new Observer<List<Concerts>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onChanged(List<Concerts> concertsList) {
                Log.d(TAG, "onChanged: " +concertsList.size());
                cAdapter = new ConcertsAndTheatreAdapter(concertsList, ConcertsAndTheatresActivity.this, ConcertsAndTheatresActivity.this::onSingleConcertItemClicked );
                mRecycler.setAdapter(cAdapter);
            }
        });
    }

    @Override
    public void onSingleConcertItemClicked(int position) {
        Intent intent = new Intent(ConcertsAndTheatresActivity.this, MoreInfo.class);
        startActivity(intent);
    }
}
