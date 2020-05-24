package com.wadektech.eventshub.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "friends_events")
class FriendsEvents(
        var title: String,
        var shortDesc: String,
        var date: Long,
        var location: String,
        var entryFees: Int,

        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
)