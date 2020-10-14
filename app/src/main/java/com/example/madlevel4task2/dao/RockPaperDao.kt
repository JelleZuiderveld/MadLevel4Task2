package com.example.madlevel4task2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel4task2.model.RockPaper

@Dao
interface RockPaperDao {

    @Query("SELECT * FROM rock_paper_table")
    suspend fun getAllGames(): List<RockPaper>

    @Insert
    suspend fun insertGame(rockPaper: RockPaper)

    @Delete
    suspend fun deleteGame(rockPaper: RockPaper)

    @Query("DELETE FROM rock_paper_table")
    suspend fun deleteAllProducts()
}