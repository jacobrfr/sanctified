package com.google.apps.sanctified.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.apps.sanctified.data.PrayerRepository
import kotlinx.coroutines.launch

class PrayerViewModel(
        private val prayerRepository: PrayerRepository,
        private val subject: String,
        private val description: String
) : ViewModel() {
    fun addPrayer() {
        viewModelScope.launch {
            prayerRepository.createPrayer(subject, description)
        }
    }
}