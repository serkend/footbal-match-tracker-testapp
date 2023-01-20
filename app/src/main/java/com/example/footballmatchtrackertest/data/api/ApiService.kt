package com.example.cfttestapp.data.api

import com.example.footballmatchtrackertest.data.model.MatchesInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("fixtures/?user=monsterkil61&token=f89773782dc5f970bd5114a33a17a3a2&t=schedule")
    suspend fun getAllMatchesInfo(@Query("d") date:String) : Response<MatchesInfo>

}