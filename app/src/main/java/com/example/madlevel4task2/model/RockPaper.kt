package com.example.madlevel4task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "rock_paper_table")
data class RockPaper(

    @ColumnInfo(name = "compResult")
    public var compResult :Int,

    @ColumnInfo(name = "playerResult")
    public var playerResult : Int,

    @ColumnInfo(name = "result")
    val result: Result?,

    @ColumnInfo(name = "date")
    public var date : Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
){
    enum class Result(val value: Int){
        WIN(0),
        DRAW(1),
        LOSS(2)
    }

    enum class Pick{
        ROCK,
        PAPER,
        SCISSORS
    }
}