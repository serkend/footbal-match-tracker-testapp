package com.example.footballmatchtrackertest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballmatchtrackertest.db.MatchesDatabase

class MainFactory(private val db: MatchesDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(db) as T
    }
}
