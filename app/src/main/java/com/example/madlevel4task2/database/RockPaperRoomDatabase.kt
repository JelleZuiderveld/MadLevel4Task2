package com.example.madlevel4task2.database

import android.content.Context
import androidx.room.*
import com.example.madlevel4task2.dao.RockPaperDao
import com.example.madlevel4task2.model.Converter
import com.example.madlevel4task2.model.RockPaper

@Database(entities = [RockPaper::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class RockPaperRoomDatabase : RoomDatabase(){

    abstract fun dao() : RockPaperDao

    companion object{
        private const val DATABASE_NAME = "ROCK_PAPER_DATABASE"

        @Volatile
        private var rockPaperRoomDatabaseInstance: RockPaperRoomDatabase? = null

        fun getDatabase(context: Context): RockPaperRoomDatabase? {
            if(rockPaperRoomDatabaseInstance == null){
                synchronized(RockPaperRoomDatabase::class.java){
                    if(rockPaperRoomDatabaseInstance == null){
                        rockPaperRoomDatabaseInstance =

                            Room.databaseBuilder(context.applicationContext,RockPaperRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return rockPaperRoomDatabaseInstance
        }
    }
}