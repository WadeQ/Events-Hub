package com.wadektech.eventshub.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wadektech.eventshub.models.*
import timber.log.Timber

@Database(entities = [MainEvents::class, ProfessionalEvents::class, SocialEvents::class,
    Concerts::class, FriendsEvents::class], version = 10, exportSchema = false)
abstract class MainEventsRoomDatabase : RoomDatabase() {
    abstract fun mainEventsDao(): MainEventsDao
    abstract fun pofessionalEventsDao(): PofessionalEventsDao
    abstract fun socialEventsDao(): SocialEventsDao
    abstract fun concertsDao(): ConcertsDao
    abstract fun friendsEventDao(): FriendsEventDao

    companion object {
        private var rInstance: MainEventsRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = rInstance ?: synchronized(LOCK){
            rInstance  ?: getInstance(context.applicationContext).also {
                Timber.d("invoke(): $it has been created")
                rInstance = it
            }
        }

        fun getInstance(context: Context) = Room.databaseBuilder(
                                context.applicationContext,
                                MainEventsRoomDatabase::class.java,
                                "EventsRoomDB")
                                .fallbackToDestructiveMigration()
                                .build()
        }

}