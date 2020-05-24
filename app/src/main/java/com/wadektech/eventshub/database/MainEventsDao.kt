package com.wadektech.eventshub.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wadektech.eventshub.models.MainEvents

@Dao
interface MainEventsDao {
    @Query("SELECT * FROM events_hub ORDER BY date")
    fun getAllMainEventsFromRoom(): LiveData<List<MainEvents>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllEventsToRoom(mainEventsList: MutableList<MainEvents>)
}