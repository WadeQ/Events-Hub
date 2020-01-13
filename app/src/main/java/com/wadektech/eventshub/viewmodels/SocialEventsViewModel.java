package com.wadektech.eventshub.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.wadektech.eventshub.database.MainEventsRoomDatabase;
import com.wadektech.eventshub.models.SocialEvents;
import java.util.List;

public class SocialEventsViewModel extends AndroidViewModel {
    public static MainEventsRoomDatabase mainEventsRoomDatabase;
    public LiveData<List<SocialEvents>> allSocialEvents ;


    public SocialEventsViewModel(Application application) {
        super(application);
        mainEventsRoomDatabase = MainEventsRoomDatabase.getInstance(application);
        allSocialEvents = mainEventsRoomDatabase.socialEventsDao().getAllSocialEvents();

    }

    public LiveData<List<SocialEvents>> getAllSocialEvents(){
        return allSocialEvents;
    }
}
