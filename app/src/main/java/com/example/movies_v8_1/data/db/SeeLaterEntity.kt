package com.example.movies_v8_1.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SeeLater")
class SeeLaterEntity(
    @PrimaryKey
    var kinopoiskID: Long,
    var nameRu: String,
    var posterURLPreview: String,
    var date: Long
)
