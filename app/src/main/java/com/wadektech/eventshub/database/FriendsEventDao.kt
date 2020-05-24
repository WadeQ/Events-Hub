package com.wadektech.eventshub.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wadektech.eventshub.models.FriendsEvents

@Dao
interface FriendsEventDao {
    @Query("SELECT * FROM friends_events ORDER BY date")
    fun getAllFriendsEvents(): LiveData<List<FriendsEvents>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllFriendsEventsToRoom(friendsEventsList: MutableList<FriendsEvents>)
}