package com.example.testone.data.remote

import com.example.testone.data.dto.ListItem
import com.example.testone.domain.model.RegisterStatus
import com.example.testone.domain.model.RegistrationForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("users")
    suspend fun getUserList(): Response<List<ListItem>>

    @POST("register.php")
    suspend fun register(@Body registrationForm: RegistrationForm): Response<RegisterStatus>

    @GET("")
    suspend fun getAuthUser()
}
