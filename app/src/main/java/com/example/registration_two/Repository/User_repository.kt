package com.example.registration_two.Repository

import com.example.registration_two.API.ApiClient
import com.example.registration_two.API.Api_interface
import com.example.registration_two.models.RegistrationRequest
import com.example.registration_two.models.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class User_repository {
    var retrofit=ApiClient.buildApiClient(Api_interface::class.java)

  suspend  fun registerStudent(registrationRequest:RegistrationRequest):Response<RegistrationResponse> =
    withContext(Dispatchers.IO){
        var response=retrofit.registerStudent(registrationRequest)
        return@withContext response
    }
}