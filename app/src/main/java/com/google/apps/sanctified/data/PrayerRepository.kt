package com.google.apps.sanctified.data

class PrayerRepository private constructor(private val prayerDao: PrayerDao) {
    fun getPrayers() = prayerDao.getPrayers()

    suspend fun createPrayer(prayerSubject: String, prayerDescription: String) {
        val prayer = Prayer(prayerSubject, prayerDescription)
        prayerDao.insertPrayer(prayer)
    }

    suspend fun deletePrayer(prayer: Prayer) {
        prayerDao.deletePrayer(prayer)
    }

    companion object {
        @Volatile private var instance: PrayerRepository? = null

        fun getInstance(prayerDao: PrayerDao) =
            instance ?: synchronized(this) {
                instance ?: PrayerRepository(prayerDao).also { instance = it }
            }
    }
}