package com.example.registration_two.Repository

import com.example.registration_two.API.ApiClient
import com.example.registration_two.API.Api_interface
import com.example.registration_two.models.Courses
import com.example.registration_two.models.EnrollmentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class Course_Repository {
    var retrofit=ApiClient.buildApiClient(Api_interface::class.java)

    suspend fun fetchCourses(acces_token:String):Response<List<Courses>> =
        withContext(Dispatchers.IO){
            return@withContext retrofit.getCourses(acces_token)
        }

    suspend fun enrol(acces_token:String):Response<EnrollmentResponse> =
        withContext(Dispatchers.IO){
            return@withContext retrofit.enrol(acces_token)
        }

}