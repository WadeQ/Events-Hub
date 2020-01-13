package com.wadektech.eventshub.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.wadektech.eventshub.models.Concerts;
import java.util.List;

@Dao
public interface ConcertsDao {

    @Query("SELECT * FROM Concerts ORDER BY date")
    LiveData<List<Concerts>> getAllConcertsAndTheatres();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAllConcertsAndTheatres(List<Concerts> concertsList);
}
