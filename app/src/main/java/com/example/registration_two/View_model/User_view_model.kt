package com.example.registration_two.View_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration_two.Repository.User_repository
import com.example.registration_two.models.RegistrationRequest
import com.example.registration_two.models.RegistrationResponse
import kotlinx.coroutines.launch

class User_view_model:ViewModel() {
    var userRepository=User_repository()
    var registrationLiveData=MutableLiveData<RegistrationResponse>()
    var errorLiveData=MutableLiveData<String>()

    fun registerStudent(regRequest: RegistrationRequest) {
        viewModelScope.launch {
            var response=userRepository.registerStudent(regRequest)
            if (response.isSuccessful){
                registrationLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())

            }

        }
    }

}