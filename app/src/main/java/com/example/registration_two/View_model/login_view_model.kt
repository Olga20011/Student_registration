package com.example.registration_two.View_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration_two.Repository.Login_repository
import com.example.registration_two.Repository.User_repository
import com.example.registration_two.models.LoginRequest
import com.example.registration_two.models.LoginResponse
import kotlinx.coroutines.launch

class login_view_model:ViewModel() {

        var loginRepository= Login_repository()
        var loginLiveData= MutableLiveData<LoginResponse>()
        var erroLiveData= MutableLiveData<String>()

        fun loginStudent(LoginRegister: LoginRequest){
                viewModelScope.launch {
                        var response=loginRepository.loginStudent(LoginRegister)
                        if (response.isSuccessful){
                                loginLiveData.postValue(response.body())
                        }
                        else{
                                erroLiveData.postValue(response.errorBody()?.string())
                        }

                }

        }


}
