package com.example.footballmatchtrackertest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.footballmatchtrackertest.db.dao.MatchesDao
import com.example.footballmatchtrackertest.db.model.MatchesInfoEntity

@Database(entities = [MatchesInfoEntity::class], version = 1)
abstract class MatchesDatabase : RoomDatabase() {
    abstract fun getDao(): MatchesDao

    companion object {
        @Volatile
        private var INSTANCE: MatchesDatabase? = null

        fun getDataBase(context: Context): MatchesDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MatchesDatabase::class.java,
                        "match_list.db"
                    ).fallbackToDestructiveMigration().build()
                    instance
                }
        }
    }

}