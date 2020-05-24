package com.wadektech.eventshub.repository

import androidx.lifecycle.LiveData
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

    fun getSocialEvents() : LiveData<List<SocialEvents>>{
        return database.socialEventsDao().getAllSocialEvents()
    }

    fun getProfessionalEvents() : LiveData<List<ProfessionalEvents>>{
        return database.pofessionalEventsDao().getAllProfEventsFromRoom()
    }

    fun getFriendEvent() : LiveData<List<FriendsEvents>>{
        return database.friendsEventDao().getAllFriendsEvents()
    }

    fun getMainEvents() : LiveData<List<MainEvents>>{
        return database.mainEventsDao().getAllMainEventsFromRoom()
    }

    fun getConcerts() : LiveData<List<Concerts>>{
        return database.concertsDao().getAllConcertsAndTheatres()
    }
}