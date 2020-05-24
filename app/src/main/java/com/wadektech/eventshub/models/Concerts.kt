package com.wadektech.eventshub.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "concerts", indices = [Index(value = ["location"], unique = true)])
class Concerts (
        val title: String,
        val shortDesc: String,
        val date: Long,
        val location: String,
        val entryFees: Int,

        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
)