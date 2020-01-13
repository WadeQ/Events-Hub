package com.wadektech.eventshub.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.wadektech.eventshub.database.MainEventsRoomDatabase;
import com.wadektech.eventshub.models.ProfessionalEvents;
import java.util.List;

public class ProfessionalEventsViewModel extends AndroidViewModel {
    public static MainEventsRoomDatabase mainEventsRoomDatabase ;
    public LiveData<List<ProfessionalEvents>> allProfessionalEvents ;

    public ProfessionalEventsViewModel(Application application) {
        super(application);
        mainEventsRoomDatabase = MainEventsRoomDatabase.getInstance(application);
        allProfessionalEvents = mainEventsRoomDatabase.pofessionalEventsDao().getAllProfEventsFromRoom();
    }

    public LiveData<List<ProfessionalEvents>> getAllProfessionalEvents(){
        return allProfessionalEvents ;
    }
}
