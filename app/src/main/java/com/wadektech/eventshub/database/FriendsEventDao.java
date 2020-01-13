package com.wadektech.eventshub.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wadektech.eventshub.models.FriendsEvents;

import java.util.List;

@Dao
public interface FriendsEventDao {

    @Query("SELECT * FROM friends_events ORDER BY date")
    LiveData<List<FriendsEvents>> getAllFriendsEvents();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAllFriendsEventsToRoom(List<FriendsEvents> friendsEventsList);
}
