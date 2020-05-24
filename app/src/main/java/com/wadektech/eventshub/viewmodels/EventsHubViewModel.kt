package com.wadektech.eventshub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wadektech.eventshub.models.*
import com.wadektech.eventshub.repository.EventsHubRepository
import kotlinx.coroutines.launch
import java.util.ArrayList

class EventsHubViewModel(private val repository: EventsHubRepository) : ViewModel() {
    var professionalEvents: MutableList<ProfessionalEvents> = ArrayList()
    var socialEvents: MutableList<SocialEvents> = ArrayList()
    var concertsAndTheatres: MutableList<Concerts> = ArrayList()
    var friendsEvents: MutableList<FriendsEvents> = ArrayList()
    private var mainEvents: MutableList<MainEvents> = ArrayList()


    private fun saveMainEvents(mainLiveData: MutableList<MainEvents>) = viewModelScope.launch {
        repository.saveAllMainEvents(mainLiveData)
    }

    private fun saveConcerts(concertsLiveData: MutableList<Concerts>) = viewModelScope.launch {
        repository.saveConcertsToRoom(concertsLiveData)
    }

    private fun saveFriendsEvents(friendsEventsLiveData: MutableList<FriendsEvents>) = viewModelScope.launch {
        repository.saveFriendsEventsToRoom(friendsEventsLiveData)
    }

    private fun saveProfessionalEvents(professionalLiveData: MutableList<ProfessionalEvents>) = viewModelScope.launch {
        repository.saveProfessionalEventsToRoom(professionalLiveData)
    }

    private fun saveSocialEvents(socialLiveData: MutableList<SocialEvents>) = viewModelScope.launch {
        repository.saveSocialEventsToRoom(socialLiveData)
    }

    fun getAllConcertsFromRoom(): LiveData<List<Concerts>> {
        return repository.getConcerts()
    }

    fun getAllMainEvents(): LiveData<List<MainEvents>> {
        return repository.getMainEvents()
    }

    fun getAllProfEvents(): LiveData<List<ProfessionalEvents>> {
        return repository.getProfessionalEvents()
    }

    fun getAllSocialEvents(): LiveData<List<SocialEvents>> {
        return repository.getSocialEvents()
    }

    fun getAllFriendsEvents(): LiveData<List<FriendsEvents>> {
        return repository.getFriendEvent()
    }



    fun allProfEvents() : MutableLiveData<List<ProfessionalEvents>> {
        professionalEvents.add(ProfessionalEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23 - 9 - 2019, "Ihub Westlands", 200))
        professionalEvents.add(ProfessionalEvents("Android DevFest Kisumu", "Meetup for Android Developers", 23 - 9 - 2019, "Lakehub Kisumu", 200))
        professionalEvents.add(ProfessionalEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23 - 9 - 2019, "Swahilibox Mombasa", 200))
        professionalEvents.add(ProfessionalEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23 - 9 - 2019, "Swahilipot Mombasa", 200))
        professionalEvents.add(ProfessionalEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23 - 9 - 2019, "Andela HQ. Nairobi", 200))
        professionalEvents.add(ProfessionalEvents("Android DevFest UoN", "Meetup for Android Developers", 23 - 9 - 2019, "GDG UoN", 200))
        val listMutableLiveData = MutableLiveData<List<ProfessionalEvents>>()
        listMutableLiveData.value = professionalEvents
        saveProfessionalEvents(professionalEvents)
        return listMutableLiveData
    }
    fun allMainEventsRoom(): MutableLiveData<List<MainEvents>> {
        mainEvents.add(MainEvents("Android DevFest UoN", "Meetup for Android Developers", 23 - 9 - 2019, "GDG UoN", 200))
        mainEvents.add(MainEvents("Android DevFest Kisumu", "Meetup for Android Developers", 23 - 9 - 2019, "Lakehub Kisumu", 200))
        mainEvents.add(MainEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23 - 9 - 2019, "Swahilibox Mombasa", 200))
        mainEvents.add(MainEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23 - 9 - 2019, "Swahilipot Mombasa", 200))
        mainEvents.add(MainEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23 - 9 - 2019, "Andela HQ. Nairobi", 200))
        mainEvents.add(MainEvents("Android DevFest UoN", "Meetup for Android Developers", 23 - 9 - 2019, "GDG UoN", 200))
        val listMutableLiveData = MutableLiveData<List<MainEvents>>()
        listMutableLiveData.value = mainEvents
        saveMainEvents(mainEvents)
        return listMutableLiveData
    }
    fun allSocialEvents(): MutableLiveData<List<SocialEvents>> {
        socialEvents.add(SocialEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23 - 9 - 2019, "Ihub Westlands", 200))
        socialEvents.add(SocialEvents("Android DevFest Kisumu", "Meetup for Android Developers", 23 - 9 - 2019, "Lakehub Kisumu", 200))
        socialEvents.add(SocialEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23 - 9 - 2019, "Swahilibox Mombasa", 200))
        socialEvents.add(SocialEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23 - 9 - 2019, "Swahilipot Mombasa", 200))
        socialEvents.add(SocialEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23 - 9 - 2019, "Andela HQ. Nairobi", 200))
        socialEvents.add(SocialEvents("Android DevFest UoN", "Meetup for Android Developers", 23 - 9 - 2019, "GDG UoN", 200))
        val listMutableLiveData = MutableLiveData<List<SocialEvents>>()
        listMutableLiveData.value = socialEvents
        saveSocialEvents(socialEvents)
        return listMutableLiveData
    }

    fun allConcertsAndTheatres(): MutableLiveData<List<Concerts?>> {
        concertsAndTheatres.add(Concerts("Android DevFest Nairobi", "Meetup for Android Developers", 23 - 9 - 2019, "Ihub Westlands", 200))
        concertsAndTheatres.add(Concerts("Android DevFest Kisumu", "Meetup for Android Developers", 23 - 9 - 2019, "Lakehub Kisumu", 200))
        concertsAndTheatres.add(Concerts("Android DevFest Mombasa", "Meetup for Android Developers", 23 - 9 - 2019, "Swahilibox Mombasa", 200))
        concertsAndTheatres.add(Concerts("Android DevFest Mombasa", "Meetup for Android Developers", 23 - 9 - 2019, "Swahilipot Mombasa", 200))
        concertsAndTheatres.add(Concerts("Android DevFest Nairobi", "Meetup for Android Developers", 23 - 9 - 2019, "Andela HQ. Nairobi", 200))
        concertsAndTheatres.add(Concerts("Android DevFest UoN", "Meetup for Android Developers", 23 - 9 - 2019, "GDG UoN", 200))
        val listMutableLiveData = MutableLiveData<List<Concerts?>>()
        listMutableLiveData.value = concertsAndTheatres
        saveConcerts(concertsAndTheatres)
        return listMutableLiveData
    }

    fun allFriendEventsRoom(): MutableLiveData<List<FriendsEvents>> {
        friendsEvents.add(FriendsEvents("Android DevFest UoN", "Meetup for Android Developers", 23 - 9 - 2019, "GDG UoN", 200))
        friendsEvents.add(FriendsEvents("Android DevFest Kisumu", "Meetup for Android Developers", 23 - 9 - 2019, "Lakehub Kisumu", 200))
        friendsEvents.add(FriendsEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23 - 9 - 2019, "Swahilibox Mombasa", 200))
        friendsEvents.add(FriendsEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23 - 9 - 2019, "Swahilipot Mombasa", 200))
        friendsEvents.add(FriendsEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23 - 9 - 2019, "Andela HQ. Nairobi", 200))
        friendsEvents.add(FriendsEvents("Android DevFest UoN", "Meetup for Android Developers", 23 - 9 - 2019, "GDG UoN", 200))
        val listMutableLiveData = MutableLiveData<List<FriendsEvents>>()
        listMutableLiveData.value = friendsEvents
        saveFriendsEvents(friendsEvents)
        return listMutableLiveData
    }
}