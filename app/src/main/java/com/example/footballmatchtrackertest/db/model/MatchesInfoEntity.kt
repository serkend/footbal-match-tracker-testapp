package com.example.footballmatchtrackertest.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.footballmatchtrackertest.data.model.*

@Entity(tableName = "matches_table")
data class MatchesInfoEntity(
    @PrimaryKey
    val id:Int,
    val matchJson:String
)