package com.example.registration_two.Repository

import com.example.registration_two.API.ApiClient
import com.example.registration_two.API.Api_interface
import com.example.registration_two.models.LoginRequest
import com.example.registration_two.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class Login_repository {
    var retrofit=ApiClient.buildApiClient(Api_interface::class.java)

    suspend fun loginStudent(loginRequest:LoginRequest): Response<LoginResponse> =
        withContext(Dispatchers.IO) {
            var response = retrofit.loginStudent(loginRequest)
            return@withContext response
        }
    }