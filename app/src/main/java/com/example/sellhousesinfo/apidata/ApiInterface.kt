package com.example.sellhousesinfo.apidata

import com.example.sellhousesinfo.model.HousesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("f4864c66-ee04-4e7f-88a2-2fbd912ca5ab")
    suspend fun getData(): Response<HousesModel>
}