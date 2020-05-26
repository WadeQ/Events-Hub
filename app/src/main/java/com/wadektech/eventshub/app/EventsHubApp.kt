package com.wadektech.eventshub.app

import android.app.Application
import android.app.DatePickerDialog
import com.wadektech.eventshub.database.MainEventsRoomDatabase
import com.wadektech.eventshub.repository.EventsHubRepository
import com.wadektech.eventshub.utils.EventsHubViewModelFactory
import com.wadektech.eventshub.viewmodels.EventsHubViewModel
import timber.log.Timber

class EventsHubApp : Application() {
    private lateinit var eventsHubViewModel: EventsHubViewModel

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }
    init {
        instance = this
    }

    companion object {
        var instance: EventsHubApp? = null

        fun notesApplicationContext() : EventsHubApp {
            return instance as EventsHubApp
        }
    }
}