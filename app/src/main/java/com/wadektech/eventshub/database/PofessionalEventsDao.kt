package com.wadektech.eventshub.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wadektech.eventshub.models.ProfessionalEvents

@Dao
interface PofessionalEventsDao {
    @Query("SELECT * FROM professional_events ORDER BY date")
    fun getAllProfEventsFromRoom(): DataSource.Factory<Int,ProfessionalEvents>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllProfEventsToRoom(professionalEventsList: MutableList<ProfessionalEvents>)
}