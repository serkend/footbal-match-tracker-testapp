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

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.placeHolder, MatchesInfoFragment.newInstance())
//            .commit()

//        navController = Navigation.findNavController(this, R.id.fragmentContainerView)

    // Enable verbose OneSignal logging to debug issues if needed.
    OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

    // OneSignal Initialization
    OneSignal.initWithContext(this);
    OneSignal.setAppId("33dc503c-444c-494f-a91b-d985819d0823");

    // promptForPushNotifications will show the native Android notification permission prompt.
    // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
    OneSignal.promptForPushNotifications();


    }
}