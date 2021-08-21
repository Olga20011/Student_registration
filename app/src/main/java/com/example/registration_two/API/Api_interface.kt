package com.example.registration_two.API

import android.telecom.Call
import com.example.registration_two.models.LoginRequest
import com.example.registration_two.models.RegistrationRequest
import com.example.registration_two.models.RegistrationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Api_interface {
    @POST("/student/register")
    fun registerStudent(@Body registrationRequest: RegistrationRequest):retrofit2.Call<RegistrationResponse>

    @POST("/students/login")
    fun loginStudent(@Body registrationRequest: LoginRequest): retrofit2.Call<RegistrationResponse>

}
