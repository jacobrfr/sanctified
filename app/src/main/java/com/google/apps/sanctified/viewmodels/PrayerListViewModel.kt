package com.google.apps.sanctified.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.apps.sanctified.data.Prayer
import com.google.apps.sanctified.data.PrayerRepository
import kotlinx.coroutines.launch

class PrayerListViewModel internal constructor(
        private val prayerRepository: PrayerRepository
) : ViewModel() {
    val prayers: LiveData<List<Prayer>> = prayerRepository.getPrayers()

    fun addPrayer(subject: String, description: String) {
        viewModelScope.launch {
            prayerRepository.createPrayer(subject, description)
        }
    }

    fun deletePrayer(prayer: Prayer) {
        viewModelScope.launch {
            prayerRepository.deletePrayer(prayer)
        }
    }
}