package com.wadektech.eventshub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.wadektech.eventshub.R;
import com.wadektech.eventshub.adapter.ProfessionalEventsAdapter;
import com.wadektech.eventshub.models.ProfessionalEvents;
import com.wadektech.eventshub.viewmodels.ProfessionalEventsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfessionaEventsActivity extends AppCompatActivity implements ProfessionalEventsAdapter.OnSingleProfEventClicked {
    RecyclerView mRecycler ;
    LinearLayoutManager mLayout ;
    ProfessionalEventsAdapter mProfAdapter ;
    List<ProfessionalEvents> professionalEventsList ;
    ProfessionalEventsViewModel professionalEventsViewModel ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professiona_events);

        mRecycler = findViewById(R.id.rec_prof_events);
        mLayout = new LinearLayoutManager(this) ;
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(mLayout);

        professionalEventsList = new ArrayList<>() ;

        professionalEventsViewModel = ViewModelProviders.of(this).get(ProfessionalEventsViewModel.class);
        professionalEventsViewModel.getAllProfessionalEvents().observe(this, new Observer<List<ProfessionalEvents>>() {
            @Override
            public void onChanged(List<ProfessionalEvents> professionalEventsList) {
                mProfAdapter = new ProfessionalEventsAdapter(professionalEventsList, ProfessionaEventsActivity.this, ProfessionaEventsActivity.this::singleProfessionalEventClicked);
                mRecycler.setAdapter(mProfAdapter);
            }
        });
    }

    @Override
    public void singleProfessionalEventClicked(int position) {
        Intent intent = new Intent(ProfessionaEventsActivity.this, MoreInfo.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ProfessionaEventsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
