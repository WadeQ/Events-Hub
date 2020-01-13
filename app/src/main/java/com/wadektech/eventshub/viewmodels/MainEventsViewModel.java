package com.wadektech.eventshub.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.wadektech.eventshub.database.MainEventsRoomDatabase;
import com.wadektech.eventshub.models.MainEvents;
import java.util.List;

public class MainEventsViewModel extends AndroidViewModel {
    public static MainEventsRoomDatabase mainEventsRoomDatabase;
    public LiveData<List<MainEvents>> allEvents ;

    public MainEventsViewModel(Application application){
        super(application);
       mainEventsRoomDatabase = MainEventsRoomDatabase.getInstance(application);
       allEvents = mainEventsRoomDatabase.mainEventsDao().getAllMainEventsFromRoom();
    }

    public LiveData<List<MainEvents>> getAllEvents(){
        return allEvents ;
    }
}
