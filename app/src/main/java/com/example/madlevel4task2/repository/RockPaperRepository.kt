package com.example.madlevel4task2.repository

import android.content.Context
import com.example.madlevel4task2.dao.RockPaperDao
import com.example.madlevel4task2.database.RockPaperRoomDatabase
import com.example.madlevel4task2.model.RockPaper

class RockPaperRepository (context: Context){

    private val rockPaperDao: RockPaperDao

    init {
        val database = RockPaperRoomDatabase.getDatabase(context)
        rockPaperDao = database!!.dao()
    }

    suspend fun getAllMatches(): List<RockPaper> = rockPaperDao.getAllMatches()

    suspend fun insertMatch(rockPaper: RockPaper) = rockPaperDao.insertMatch(rockPaper)

    suspend fun deleteAllMatches() = rockPaperDao.deleteAllMatches()

}