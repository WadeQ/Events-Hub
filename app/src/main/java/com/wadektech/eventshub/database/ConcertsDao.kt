package com.wadektech.eventshub.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wadektech.eventshub.models.Concerts

@Dao
interface ConcertsDao {
    @Query("SELECT * FROM Concerts ORDER BY date")
    fun getAllConcertsAndTheatres(): LiveData<List<Concerts>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllConcertsAndTheatres(concertsList: MutableList<Concerts>)
}