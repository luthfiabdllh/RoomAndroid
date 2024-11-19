package com.example.room.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_table")
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,
    @ColumnInfo(name = "character")
    val character: String,
    @ColumnInfo(name = "anime")
    val anime: String,
    @ColumnInfo(name = "english")
    val english: String,
    @ColumnInfo(name = "indo")
    val indo: String,
)
