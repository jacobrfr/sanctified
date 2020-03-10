package com.google.apps.sanctified.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.apps.sanctified.data.Prayer
import com.google.apps.sanctified.utilities.PRAYER_DATA_FILENAME
import com.google.apps.sanctified.data.SanctifiedDatabase
import kotlinx.coroutines.coroutineScope

class SanctifiedDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(PRAYER_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Prayer>>() {}.type
                    val plantList: List<Prayer> = Gson().fromJson(jsonReader, plantType)

                    val database = SanctifiedDatabase.getInstance(applicationContext)
                    database.prayerDao().insertAll(plantList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = SanctifiedDatabaseWorker::class.java.simpleName
    }
}