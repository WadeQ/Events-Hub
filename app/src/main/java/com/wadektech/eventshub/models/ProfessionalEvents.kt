package com.wadektech.eventshub.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "professional_events", indices = [Index(value = ["location"], unique = true)])
class ProfessionalEvents(
        var title: String,
        var shortDesc: String,
        var date: Long,
        var location: String,
        var entryFees: Int,
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
)