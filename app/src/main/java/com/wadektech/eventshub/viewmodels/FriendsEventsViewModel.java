package com.wadektech.eventshub.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wadektech.eventshub.database.MainEventsRoomDatabase;
import com.wadektech.eventshub.models.FriendsEvents;

import java.util.List;

public class FriendsEventsViewModel extends AndroidViewModel {
    public static MainEventsRoomDatabase mainEventsRoomDatabase;
    public LiveData<List<FriendsEvents>> allFriendsEvents ;

    public FriendsEventsViewModel(@NonNull Application application) {
        super(application);
        mainEventsRoomDatabase = MainEventsRoomDatabase.getInstance(application);
        allFriendsEvents = mainEventsRoomDatabase.friendsEventDao().getAllFriendsEvents();
    }

    public LiveData<List<FriendsEvents>> getAllFriendsEvents(){
        return allFriendsEvents;
    }
}
