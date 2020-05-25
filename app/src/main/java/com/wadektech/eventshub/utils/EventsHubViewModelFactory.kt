package com.wadektech.eventshub.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wadektech.eventshub.repository.EventsHubRepository
import com.wadektech.eventshub.viewmodels.EventsHubViewModel

@Suppress("UNCHECKED_CAST")
class EventsHubViewModelFactory(val repository: EventsHubRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventsHubViewModel(repository) as T
    }
}