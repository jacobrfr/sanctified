package com.google.apps.sanctified.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.apps.sanctified.data.PrayerRepository

class PrayerViewModelFactory(
        private val prayerRepository: PrayerRepository,
        private val subject: String,
        private val description: String
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        return PrayerViewModel(prayerRepository, subject, description) as T
    }
}