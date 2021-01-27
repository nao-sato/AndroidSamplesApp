package com.example.ktAndroidSample.room

import androidx.room.*

@Dao
interface SampleDao {

    @Query("SELECT * FROM name where id = :id")
    fun getUser(id: Int): SampleEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(sampleEntity: SampleEntity)

    @Delete
    fun delete(sampleEntity: SampleEntity)
}
//A failure occurred while executing org.jetbrains.kotlin.gradle.internal.KaptExecution
//上のエラーが来たら大体Daoの記述がおかしい。