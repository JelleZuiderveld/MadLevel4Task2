package com.example.madlevel4task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rock_paper_table")
data class RockPaper(

    @ColumnInfo(name = "compResult")
    public var compResult : String,

    @ColumnInfo(name = "playerResult")
    public var playerResult : String,

    @ColumnInfo(name = "date")
    public var date : String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)