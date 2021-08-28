package com.example.registration_two.API

import android.telecom.Call
import com.example.registration_two.models.LoginRequest
import com.example.registration_two.models.RegistrationRequest
import com.example.registration_two.models.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api_interface {
    @POST("/student/register")
     suspend fun registerStudent(@Body registrationRequest: RegistrationRequest):Response<RegistrationResponse>

    @POST("/students/login")
    suspend fun loginStudent(@Body registrationRequest: LoginRequest): Response<RegistrationResponse>

}
