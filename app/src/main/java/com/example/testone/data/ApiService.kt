package com.example.testone.data

import com.example.testone.data.model.ListItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUserList() : Response<List<ListItem>>

}