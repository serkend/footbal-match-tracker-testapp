package com.example.footballmatchtrackertest.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.footballmatchtrackertest.R
import com.example.footballmatchtrackertest.databinding.ActivityMainBinding
import com.example.footballmatchtrackertest.ui.fragments.MatchesInfoFragment
import com.example.footballmatchtrackertest.ui.fragments.SplashFragment
import com.onesignal.OneSignal

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    //  lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}