package com.example.registration_two.API


import com.example.registration_two.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api_interface {
    @POST("/student/register")
     suspend fun registerStudent(@Body registrationRequest: RegistrationRequest):Response<RegistrationResponse>

    @POST("/student/login")
    suspend fun loginStudent(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/courses")
    suspend fun getCourses(@Header("Authorization")token:String):Response<List<Courses>>

    @POST("/enrolments")
    suspend fun enrol(@Header("Authorization") token: String): Response<EnrollmentResponse>
}

