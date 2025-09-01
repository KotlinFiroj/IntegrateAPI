package com.example.testone.data.dto.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "access_token")
    val accessToken: String = "",
    @Json(name = "expires_in")
    val expiresIn: Int = 0,
    @Json(name = "refresh_token")
    val refreshToken: String = "",
    @Json(name = "token_type")
    val tokenType: String = "",
)
