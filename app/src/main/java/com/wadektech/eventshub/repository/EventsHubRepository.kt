package com.wadektech.eventshub.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.wadektech.eventshub.database.MainEventsRoomDatabase
import com.wadektech.eventshub.models.*


class EventsHubRepository(private val database: MainEventsRoomDatabase) {


    suspend fun saveAllMainEvents(mainEvents: MutableList<MainEvents>) {
                database.mainEventsDao().saveAllEventsToRoom(mainEvents)
    }

    suspend fun saveProfessionalEventsToRoom(profEventsLiveData: MutableList<ProfessionalEvents>) {
            database.pofessionalEventsDao().saveAllProfEventsToRoom(profEventsLiveData)
    }

    suspend fun saveSocialEventsToRoom(socialEventsLiveData: MutableList<SocialEvents>) {
        database.socialEventsDao().saveAllSocialEventsIntoRoom(socialEventsLiveData)
    }

    suspend fun saveConcertsToRoom(saveConcertsLiveData: MutableList<Concerts>) {
        database.concertsDao().saveAllConcertsAndTheatres(saveConcertsLiveData)
    }

    suspend fun saveFriendsEventsToRoom(saveFriendsLiveData: MutableList<FriendsEvents>) {
       database.friendsEventDao().saveAllFriendsEventsToRoom(saveFriendsLiveData)
    }

    fun getSocialEvents() : DataSource.Factory<Int,SocialEvents>{
        return database.socialEventsDao().getAllSocialEvents()
    }

    fun getProfessionalEvents() : DataSource.Factory<Int, ProfessionalEvents>{
        return database.pofessionalEventsDao().getAllProfEventsFromRoom()
    }

    fun getFriendEvent() : DataSource.Factory<Int, FriendsEvents>{
        return database.friendsEventDao().getAllFriendsEvents()
    }

    fun getMainEvents() : DataSource.Factory<Int ,MainEvents>{
        return database.mainEventsDao().getAllMainEventsFromRoom()
    }

    fun getConcerts() : DataSource.Factory<Int,Concerts>{
        return database.concertsDao().getAllConcertsAndTheatres()
    }
}