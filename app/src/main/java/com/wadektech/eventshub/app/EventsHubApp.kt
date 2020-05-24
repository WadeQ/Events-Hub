package com.wadektech.eventshub.app

import android.app.Application
import android.app.DatePickerDialog
import timber.log.Timber

class EventsHubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
    init {
        instance = this
    }

    companion object {
        private var instance: EventsHubApp? = null

        fun notesApplicationContext() : EventsHubApp {
            return instance as EventsHubApp
        }
    }
}