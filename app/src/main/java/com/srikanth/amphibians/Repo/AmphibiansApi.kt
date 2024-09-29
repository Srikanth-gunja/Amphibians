package com.srikanth.amphibians.Repo

import com.srikanth.amphibians.Model.Amphibians
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


val BASE_URL="https://android-kotlin-fun-mars-server.appspot.com/"

var retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface AmphibiansApi{
    @GET("amphibians")
    suspend fun getAmphibians():List<Amphibians>
}