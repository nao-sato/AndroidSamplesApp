package com.example.ktAndroidSample.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SampleEntity::class], version = 1)
abstract class SampleDB: RoomDatabase() {

    abstract fun userDao(): SampleDao

    companion object {

        @Volatile
        private var instance: SampleDB? = null

        fun getInstance(context: Context): SampleDB = instance ?: synchronized(this) {
                Room.databaseBuilder(context, SampleDB::class.java, "thchbooster.db").build()
            }
    }
}

