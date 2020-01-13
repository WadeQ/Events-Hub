package com.wadektech.eventshub.repository;

import androidx.lifecycle.MutableLiveData;
import com.wadektech.eventshub.database.MainEventsRoomDatabase;
import com.wadektech.eventshub.models.Concerts;
import com.wadektech.eventshub.models.FriendsEvents;
import com.wadektech.eventshub.models.MainEvents;
import com.wadektech.eventshub.app.EventsHubApp;
import com.wadektech.eventshub.models.ProfessionalEvents;
import com.wadektech.eventshub.models.SocialEvents;
import com.wadektech.eventshub.utils.MainExecutorService;
import java.util.ArrayList;
import java.util.List;

public class EventsHubRepository {
    public static volatile EventsHubRepository sInstance ;
    public static Object LOCK = new Object() ;
    public List<MainEvents> events = new ArrayList<>();
    public List<ProfessionalEvents> professionalEvents = new ArrayList<>();
    public List<SocialEvents> socialEvents = new ArrayList<>() ;
    public List<Concerts> concertsAndTheatres = new ArrayList<>();
    public List<FriendsEvents> friendsEvents = new ArrayList<>();


    public EventsHubRepository() {
    }

    public static synchronized EventsHubRepository getInstance(){
        if (sInstance == null){
            synchronized (LOCK){
                if (sInstance == null){
                    sInstance = new EventsHubRepository() ;
                }
            }
        }
        return sInstance ;
    }

    public MutableLiveData<List<MainEvents>> getAllMainEvents(){
        events.add(new MainEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23-9-2019, "Ihub Westlands", 200));
        events.add(new MainEvents("Android DevFest Kisumu", "Meetup for Android Developers", 23-9-2019, "Lakehub Kisumu", 200));
        events.add(new MainEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23-9-2019, "Swahilibox Mombasa", 200));
        events.add(new MainEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23-9-2019, "Swahilipot Mombasa", 200));
        events.add(new MainEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23-9-2019, "Andela HQ. Nairobi", 200));
        events.add(new MainEvents("Android DevFest UoN", "Meetup for Android Developers", 23-9-2019, "GDG UoN", 200));

        final MutableLiveData<List<MainEvents>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(events);
        saveToRoom();
        return listMutableLiveData ;
    }

    public void saveToRoom(){
       MainExecutorService
               .getInstance()
               .getDiskIO()
               .execute(() -> MainEventsRoomDatabase
               .getInstance(
                       EventsHubApp
                               .getApp()
                               .getApplicationContext())
               .mainEventsDao()
               .saveAllEventsToRoom(events));
    }

    public MutableLiveData<List<ProfessionalEvents>> getAllProfEvents(){
        professionalEvents.add(new ProfessionalEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23-9-2019, "Ihub Westlands", 200));
        professionalEvents.add(new ProfessionalEvents("Android DevFest Kisumu", "Meetup for Android Developers", 23-9-2019, "Lakehub Kisumu", 200));
        professionalEvents.add(new ProfessionalEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23-9-2019, "Swahilibox Mombasa", 200));
        professionalEvents.add(new ProfessionalEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23-9-2019, "Swahilipot Mombasa", 200));
        professionalEvents.add(new ProfessionalEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23-9-2019, "Andela HQ. Nairobi", 200));
        professionalEvents.add(new ProfessionalEvents("Android DevFest UoN", "Meetup for Android Developers", 23-9-2019, "GDG UoN", 200));

        final MutableLiveData<List<ProfessionalEvents>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(professionalEvents);
        saveProfessionalEventsToRoom();
        return listMutableLiveData ;
    }

    public void saveProfessionalEventsToRoom(){
        MainExecutorService
                .getInstance()
                .getDiskIO()
                .execute(() -> MainEventsRoomDatabase
                        .getInstance(EventsHubApp.getApp().getApplicationContext())
                        .pofessionalEventsDao()
                        .saveAllProfEventsToRoom(professionalEvents));
    }

    public MutableLiveData<List<SocialEvents>> getAllSocialEvents(){
        socialEvents.add(new SocialEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23-9-2019, "Ihub Westlands", 200));
        socialEvents.add(new SocialEvents("Android DevFest Kisumu", "Meetup for Android Developers", 23-9-2019, "Lakehub Kisumu", 200));
        socialEvents.add(new SocialEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23-9-2019, "Swahilibox Mombasa", 200));
        socialEvents.add(new SocialEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23-9-2019, "Swahilipot Mombasa", 200));
        socialEvents.add(new SocialEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23-9-2019, "Andela HQ. Nairobi", 200));
        socialEvents.add(new SocialEvents("Android DevFest UoN", "Meetup for Android Developers", 23-9-2019, "GDG UoN", 200));

        final MutableLiveData<List<SocialEvents>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(socialEvents);
        saveSocialEventsToRoom();
        return listMutableLiveData ;
    }

    public void saveSocialEventsToRoom(){
        MainExecutorService
                .getInstance()
                .getDiskIO()
                .execute(() -> MainEventsRoomDatabase
                        .getInstance(EventsHubApp.getApp().getApplicationContext())
                        .socialEventsDao()
                        .saveAllSocialEventsIntoRoom(socialEvents));
    }

    public MutableLiveData<List<Concerts>> getAllConcertsAndTheatres(){
        concertsAndTheatres.add(new Concerts("Android DevFest Nairobi", "Meetup for Android Developers", 23-9-2019, "Ihub Westlands", 200));
        concertsAndTheatres.add(new Concerts("Android DevFest Kisumu", "Meetup for Android Developers", 23-9-2019, "Lakehub Kisumu", 200));
        concertsAndTheatres.add(new Concerts("Android DevFest Mombasa", "Meetup for Android Developers", 23-9-2019, "Swahilibox Mombasa", 200));
        concertsAndTheatres.add(new Concerts("Android DevFest Mombasa", "Meetup for Android Developers", 23-9-2019, "Swahilipot Mombasa", 200));
        concertsAndTheatres.add(new Concerts("Android DevFest Nairobi", "Meetup for Android Developers", 23-9-2019, "Andela HQ. Nairobi", 200));
        concertsAndTheatres.add(new Concerts("Android DevFest UoN", "Meetup for Android Developers", 23-9-2019, "GDG UoN", 200));

        final MutableLiveData<List<Concerts>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(concertsAndTheatres);
        saveConcertsToRoom();
        return listMutableLiveData ;
    }

    public void saveConcertsToRoom(){
        MainExecutorService
                .getInstance()
                .getDiskIO()
                .execute(() -> MainEventsRoomDatabase
                        .getInstance(EventsHubApp.getApp().getApplicationContext())
                        .concertsDao()
                        .saveAllConcertsAndTheatres(concertsAndTheatres));
    }

    public MutableLiveData<List<FriendsEvents>> getAllFriendsEvents(){
        friendsEvents.add(new FriendsEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23-9-2019, "Ihub Westlands", 200));
        friendsEvents.add(new FriendsEvents("Android DevFest Kisumu", "Meetup for Android Developers", 23-9-2019, "Lakehub Kisumu", 200));
        friendsEvents.add(new FriendsEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23-9-2019, "Swahilibox Mombasa", 200));
        friendsEvents.add(new FriendsEvents("Android DevFest Mombasa", "Meetup for Android Developers", 23-9-2019, "Swahilipot Mombasa", 200));
        friendsEvents.add(new FriendsEvents("Android DevFest Nairobi", "Meetup for Android Developers", 23-9-2019, "Andela HQ. Nairobi", 200));
        friendsEvents.add(new FriendsEvents("Android DevFest UoN", "Meetup for Android Developers", 23-9-2019, "GDG UoN", 200));

        final MutableLiveData<List<FriendsEvents>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(friendsEvents);
        saveFriendsEventsToRoom();
        return listMutableLiveData ;
    }

    public void saveFriendsEventsToRoom(){
        MainExecutorService
                .getInstance()
                .getDiskIO()
                .execute(() -> MainEventsRoomDatabase
                        .getInstance(EventsHubApp.getApp().getApplicationContext())
                        .friendsEventDao()
                        .saveAllFriendsEventsToRoom(friendsEvents));
    }
}
