package com.example.footballmatchtrackertest.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.footballmatchtrackertest.db.model.MatchesInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchesDao {
    @Query("SELECT * FROM matches_table")
    fun getAllMatches(): Flow<List<MatchesInfoEntity>>

    @Query("SELECT * FROM matches_table WHERE id=:id ")
    fun getMatchById(id: String):Flow< MatchesInfoEntity>

    @Insert
    suspend fun insertRequest(matchInfoEntity: MatchesInfoEntity)

    @Query("delete from matches_table where id IS :id")
    suspend fun deleteMatch(id:String)

}