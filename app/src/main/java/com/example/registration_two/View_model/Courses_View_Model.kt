package com.example.registration_two.View_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration_two.Repository.Course_Repository
import com.example.registration_two.models.Courses
import kotlinx.coroutines.launch

class Courses_View_Model:ViewModel() {
    var CoursesRepository=Course_Repository()
    var coursesLiveData=MutableLiveData<List<Courses>>()
    var errorLiveData=MutableLiveData<String>()

    fun getCourses(access_token:String){

        viewModelScope.launch {
            var response=CoursesRepository .fetchCourses(access_token)
            if (response.isSuccessful){
                coursesLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}
