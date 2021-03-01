package com.example.nhf.Profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "profileItem")
data class ProfileItem(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "number_of_games") val number_of_games: Int,
    @ColumnInfo(name = "number_of_180s") val number_of_180s: Int,
    @ColumnInfo(name = "gender") val gender: Boolean
)
