package com.example.madlevel4task2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel4task2.model.RockPaper

@Dao
interface RockPaperDao {

    @Query("SELECT * FROM rock_paper_table")
    suspend fun getAllMatches(): List<RockPaper>

    @Insert
    suspend fun insertMatch(rockPaper: RockPaper)

    @Delete
    suspend fun deleteMatch(rockPaper: RockPaper)

    @Query("DELETE FROM rock_paper_table")
    suspend fun deleteAllMatches()
}