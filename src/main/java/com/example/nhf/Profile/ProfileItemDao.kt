package com.example.nhf.Profile

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileItemDao {
    @Query("SELECT * FROM profileItem")
    fun getAll(): List<ProfileItem>

    @Insert
    fun insert(profileItems: ProfileItem): Long

    @Update
    fun update(profileItem: ProfileItem)

    @Delete
    fun deleteItem(profileItem: ProfileItem)
}
