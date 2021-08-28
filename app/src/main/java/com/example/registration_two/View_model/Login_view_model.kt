package com.example.registration_two.View_model

import androidx.lifecycle.MutableLiveData
import com.example.registration_two.Repository.User_repository
import com.example.registration_two.models.LoginRequest

class Login_view_model {
    var userRepository=User_repository()
    var loginLiveData=MutableLiveData<LoginRequest>()
    var erroLiveData=MutableLiveData<String>()


    fun LoginStudent(LoginRegister:LoginRequest){

    }
}
