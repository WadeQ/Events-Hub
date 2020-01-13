package com.wadektech.eventshub.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.wadektech.eventshub.models.MainEvents;
import java.util.List;

@Dao
public interface MainEventsDao {

    @Query("SELECT * FROM events_hub ORDER BY date")
    LiveData<List<MainEvents>> getAllMainEventsFromRoom() ;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAllEventsToRoom(List<MainEvents> mainEventsList);
}
