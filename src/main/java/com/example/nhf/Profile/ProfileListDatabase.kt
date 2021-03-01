package com.example.nhf.Profile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProfileItem::class], version = 1)
abstract class ProfileListDatabase : RoomDatabase() {
    abstract fun profileItemDAO(): ProfileItemDao

    companion object{
        @Volatile
        private var INSTANCE: ProfileListDatabase?=null

        fun getInstance(context: Context):ProfileListDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileListDatabase::class.java,
                    "profile-list"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}