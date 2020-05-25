package com.wadektech.eventshub.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wadektech.eventshub.models.SocialEvents

@Dao
interface SocialEventsDao {
    @Query("SELECT * FROM social_events ORDER BY date")
    fun getAllSocialEvents() : DataSource.Factory<Int, SocialEvents>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllSocialEventsIntoRoom(socialEventsList: MutableList<SocialEvents>)
}