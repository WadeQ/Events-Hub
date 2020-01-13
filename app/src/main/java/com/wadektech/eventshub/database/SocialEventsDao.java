package com.wadektech.eventshub.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wadektech.eventshub.models.SocialEvents;

import java.util.List;

@Dao
public interface SocialEventsDao {

    @Query("SELECT * FROM social_events ORDER BY date")
    LiveData<List<SocialEvents>> getAllSocialEvents();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAllSocialEventsIntoRoom(List<SocialEvents> socialEventsList);
}
