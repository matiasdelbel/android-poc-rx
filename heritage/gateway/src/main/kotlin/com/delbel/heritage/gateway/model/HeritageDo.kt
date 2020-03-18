package com.delbel.heritage.gateway.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heritage")
internal data class HeritageDo(
    @PrimaryKey val id: String,
    val year: Int,
    val target: String,
    val name: String,
    val type: String,
    val region: String,
    val regionLong: String,
    val coordinates: String?,
    val lat: Double,
    val lng: Double,
    val page: String,
    val image: String,
    val imageAuthor: String,
    val shortInfo: String,
    val longInfo: String?
)