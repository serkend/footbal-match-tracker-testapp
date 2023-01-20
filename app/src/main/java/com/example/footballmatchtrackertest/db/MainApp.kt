package com.example.footballmatchtrackertest.db

import android.app.Application
import com.example.footballmatchtrackertest.db.MatchesDatabase

class MainApp : Application() {
    val database by lazy { MatchesDatabase.getDataBase(this)}
}