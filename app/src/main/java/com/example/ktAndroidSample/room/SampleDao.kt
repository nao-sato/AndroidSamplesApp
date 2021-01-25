package com.example.ktAndroidSample.room

import androidx.room.*

@Dao
interface SampleDao {

    @Query("SELECT * FROM name")
    fun loadAllTodo(): SampleEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTodo(sampleEntity: SampleEntity)

    @Delete
    fun deleteTodo(sampleEntity: SampleEntity)
}