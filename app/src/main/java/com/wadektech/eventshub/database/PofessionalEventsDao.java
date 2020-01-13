package com.wadektech.eventshub.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.wadektech.eventshub.models.ProfessionalEvents;

import java.util.List;

@Dao
public interface PofessionalEventsDao {
    @Query("SELECT * FROM professional_events ORDER BY date")
    LiveData<List<ProfessionalEvents>> getAllProfEventsFromRoom() ;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAllProfEventsToRoom(List<ProfessionalEvents> professionalEventsList);
}
