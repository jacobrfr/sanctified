package com.google.apps.sanctified.data

    import androidx.lifecycle.LiveData
    import androidx.room.*

    @Dao
    interface PrayerDao {
        @Query("SELECT * FROM prayers ORDER BY prayer_date")
        fun getPrayers(): LiveData<List<Prayer>>

        @Query("SELECT * FROM prayers WHERE id = :prayerId")
        fun getPrayer(prayerId: Int) : LiveData<Prayer>

        @Insert
        suspend fun insertPrayer(prayer: Prayer)

        @Delete
        suspend fun deletePrayer(prayer: Prayer)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(prayers: List<Prayer>)
}