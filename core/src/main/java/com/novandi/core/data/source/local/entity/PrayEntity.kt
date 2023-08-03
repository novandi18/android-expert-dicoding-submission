package com.novandi.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pray")
data class PrayEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "doa")
    val doa: String,

    @ColumnInfo(name = "ayat")
    val ayat: String,

    @ColumnInfo(name = "latin")
    val latin: String,

    @ColumnInfo(name = "artinya")
    val artinya: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)