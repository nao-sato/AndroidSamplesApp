package com.example.ktAndroidSample.room

import androidx.room.Entity

@Entity(tableName = "name")
data class SampleEntity (var name: String)

//    @PrimaryKey val id: Clock = SystemClock.currentGnssTimeClock()