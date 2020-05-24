package com.wadektech.eventshub.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "events_hub", indices = [Index(value = ["location"], unique = true)])
class MainEvents(
        var title: String,
        var shortDesc: String,
        var date: Long,
        var location: String,
        var entryFees: Int,

        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
)