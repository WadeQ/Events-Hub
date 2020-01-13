package com.wadektech.eventshub.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wadektech.eventshub.models.Concerts;
import com.wadektech.eventshub.models.FriendsEvents;
import com.wadektech.eventshub.models.MainEvents;
import com.wadektech.eventshub.models.ProfessionalEvents;
import com.wadektech.eventshub.models.SocialEvents;


@Database(entities = {MainEvents.class , ProfessionalEvents.class, SocialEvents.class, Concerts.class, FriendsEvents.class},
        version = 10, exportSchema = false)
public abstract class MainEventsRoomDatabase extends RoomDatabase {
    public static MainEventsRoomDatabase rInstance ;
    public static Object LOCK = new Object() ;

    public synchronized static MainEventsRoomDatabase getInstance(Context context){
        if (rInstance == null){
            synchronized (LOCK){
                if (rInstance == null){
                    rInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MainEventsRoomDatabase.class,
                            "EventsRoomDB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return rInstance ;
    }

    public abstract MainEventsDao mainEventsDao();

    public abstract PofessionalEventsDao pofessionalEventsDao();

    public abstract SocialEventsDao socialEventsDao();

    public abstract ConcertsDao concertsDao();

    public abstract FriendsEventDao friendsEventDao();
}
