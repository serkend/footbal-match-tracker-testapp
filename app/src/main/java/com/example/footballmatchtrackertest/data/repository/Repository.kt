package com.example.footballmatchtrackertest.data.repository

import android.util.Log
import com.example.footballmatchtrackertest.data.api.RetrofitInstance
import com.example.footballmatchtrackertest.data.model.MatchesInfo
import retrofit2.Response

class Repository {

    suspend fun getAllMatchesInfo(date:String): Response<MatchesInfo> {
        Log.d("MyLog: ", "Method getAllMatchesInfo - ${RetrofitInstance.api.getAllMatchesInfo(date ).body()}")
        Log.d("MyLog: ", "Method getAllMatchesInfo - ${RetrofitInstance.api.getAllMatchesInfo(date)}")

        return RetrofitInstance.api.getAllMatchesInfo(date)
    }

}