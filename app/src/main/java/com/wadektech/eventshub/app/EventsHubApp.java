package com.wadektech.eventshub.app;

import android.app.Application;

public class EventsHubApp extends Application {
    private static EventsHubApp eventsHubApp ;

    @Override
    public void onCreate() {
        super.onCreate();
        eventsHubApp = this ;
    }

    public static EventsHubApp getApp(){
        return eventsHubApp ;
    }
}
