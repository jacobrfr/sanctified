package com.google.apps.sanctified.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.apps.sanctified.utilities.DATABASE_NAME
import com.google.apps.sanctified.workers.SanctifiedDatabaseWorker

/**
 * The Room database for this app
 */
@Database(entities = [Prayer::class], version = 1, exportSchema = false)
abstract class SanctifiedDatabase : RoomDatabase() {
    abstract fun prayerDao(): PrayerDao

    companion object {
        @Volatile private var instance: SanctifiedDatabase? = null

        fun getInstance(context: Context): SanctifiedDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): SanctifiedDatabase {
            return Room.databaseBuilder(context, SanctifiedDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SanctifiedDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    })
                    .build()
        }
    }
}