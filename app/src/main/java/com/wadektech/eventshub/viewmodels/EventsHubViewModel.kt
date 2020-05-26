package com.wadektech.eventshub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wadektech.eventshub.models.*
import com.wadektech.eventshub.repository.EventsHubRepository
import kotlinx.coroutines.launch
import java.util.ArrayList

class EventsHubViewModel(private val repository: EventsHubRepository) : ViewModel() {
    var professionalEvents: MutableList<ProfessionalEvents> = ArrayList()
    var socialEvents: MutableList<SocialEvents> = ArrayList()
    var concertsAndTheatres: MutableList<Concerts> = ArrayList()
    var friendsEvents: MutableList<FriendsEvents> = ArrayList()
    var mainEvents: MutableList<MainEvents> = ArrayList()

    private var concertsPagedList: LiveData<PagedList<Concerts>>
    private var friendsPagedList: LiveData<PagedList<FriendsEvents>>
    private var mainPagedList: LiveData<PagedList<MainEvents>>
    private var professionalPagedList: LiveData<PagedList<ProfessionalEvents>>
    private var socialPagedList: LiveData<PagedList<SocialEvents>>

    init {
         //concerts
        val factory : DataSource.Factory<Int, Concerts> = repository.getConcerts()
        val pagedListBuilder: LivePagedListBuilder<Int, Concerts> = LivePagedListBuilder<Int, Concerts>(factory,
                50)
        concertsPagedList = pagedListBuilder.build()

        //friends
        val factory2 : DataSource.Factory<Int, FriendsEvents> = repository.getFriendEvent()
        val friendsPagedListBuilder: LivePagedListBuilder<Int, FriendsEvents> = LivePagedListBuilder<Int, FriendsEvents>(factory2,
                50)
        friendsPagedList = friendsPagedListBuilder.build()

        //main events
        val factory3 : DataSource.Factory<Int, MainEvents> = repository.getMainEvents()
        val mainPagedListBuilder: LivePagedListBuilder<Int, MainEvents> = LivePagedListBuilder<Int, MainEvents>(factory3,
                50)
        mainPagedList = mainPagedListBuilder.build()

        //professional events
        val factory4 : DataSource.Factory<Int, ProfessionalEvents> = repository.getProfessionalEvents()
        val professionalPagedListBuilder: LivePagedListBuilder<Int, ProfessionalEvents> = LivePagedListBuilder<Int, ProfessionalEvents>(factory4,
                50)
        professionalPagedList = professionalPagedListBuilder.build()

        //social events
        val factory5 : DataSource.Factory<Int, SocialEvents> = repository.getSocialEvents()
        val socialPagedListBuilder: LivePagedListBuilder<Int, SocialEvents> = LivePagedListBuilder<Int, SocialEvents>(factory5,
                50)
        socialPagedList = socialPagedListBuilder.build()
    }

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

    fun getAllConcertsFromRoom(): LiveData<PagedList<Concerts>> {
        return concertsPagedList
    }

    fun getAllMainEvents(): LiveData<PagedList<MainEvents>> {
        return mainPagedList
    }

    fun getAllProfEvents(): LiveData<PagedList<ProfessionalEvents>> {
        return professionalPagedList
    }

    fun getAllSocialEvents(): LiveData<PagedList<SocialEvents>> {
        return socialPagedList
    }

    fun getAllFriendsEvents(): LiveData<PagedList<FriendsEvents>> {
        return friendsPagedList
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