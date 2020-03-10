package com.google.apps.sanctified.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.apps.sanctified.data.PrayerRepository
import com.google.apps.sanctified.data.SanctifiedDatabase
import com.google.apps.sanctified.viewmodels.PrayerListViewModelFactory

object InjectorUtils {
    private fun getPrayerRepository(context: Context) : PrayerRepository {
        return PrayerRepository.getInstance(
                SanctifiedDatabase.getInstance(context.applicationContext).prayerDao())
    }

    fun providePrayerListViewModelFactory(fragment: Fragment) : PrayerListViewModelFactory {
        val repository = getPrayerRepository(fragment.requireContext())
        return PrayerListViewModelFactory(repository, fragment)
    }
}