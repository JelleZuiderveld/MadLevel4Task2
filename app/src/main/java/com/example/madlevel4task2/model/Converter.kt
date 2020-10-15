package com.example.madlevel4task2.model

import androidx.room.TypeConverter
import java.util.*

class Converter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromResultInt(value: Int?): RockPaper.Result? {
        return RockPaper.Result.values().associateBy(RockPaper.Result::value)[value]
    }

    @TypeConverter
    fun resultToInt(result: RockPaper.Result): Int? {
        return result?.value
    }
}