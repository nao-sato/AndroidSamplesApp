package com.example.ktAndroidSample.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "name")
data class SampleEntity (@PrimaryKey val id: Int, var honorific: String, var name: String)

