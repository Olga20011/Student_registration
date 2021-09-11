package com.example.registration_two.View_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration_two.Repository.Course_Repository
import com.example.registration_two.models.EnrollmentResponse
import kotlinx.coroutines.launch

class Enrollment_View_Model:ViewModel() {

    var enrolmentLiveData =MutableLiveData<EnrollmentResponse>()
    var errorLiveData = MutableLiveData<String>()
    var coursesRepository = Course_Repository()

    fun enrol(accessToken:String){
        viewModelScope.launch{
            var response = coursesRepository.enrol(accessToken)
            if (response.isSuccessful){
                enrolmentLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

}

