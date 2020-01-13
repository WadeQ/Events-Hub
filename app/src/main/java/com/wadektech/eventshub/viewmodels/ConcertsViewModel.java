package com.wadektech.eventshub.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.wadektech.eventshub.database.MainEventsRoomDatabase;
import com.wadektech.eventshub.models.Concerts;
import java.util.List;

public class ConcertsViewModel extends AndroidViewModel {
    public static MainEventsRoomDatabase mainEventsRoomDatabase;
    public LiveData<List<Concerts>> allConcertsAndTheatres ;

    public ConcertsViewModel(Application application) {
        super(application);
        mainEventsRoomDatabase = MainEventsRoomDatabase.getInstance(application);
        allConcertsAndTheatres = mainEventsRoomDatabase.concertsDao().getAllConcertsAndTheatres();
    }

    public LiveData<List<Concerts>> getAllConcertsAndTheatres(){
        return allConcertsAndTheatres;
    }
}
