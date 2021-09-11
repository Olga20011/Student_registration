package com.example.registration_two.UI

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.registration_two.Constants
import com.example.registration_two.R
import com.example.registration_two.View_model.Courses_View_Model
import com.example.registration_two.courses_recyclerview_adaptervar
import com.example.registration_two.databinding.ActivityCoursesBinding
import com.example.registration_two.models.Courses
import retrofit2.Response.error

class CoursesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoursesBinding
    lateinit var sharedPrefs:SharedPreferences
    val courses_view_model:Courses_View_Model by viewModels()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding= ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs= getSharedPreferences(Constants.REGISTRATION_PREFS,Context.MODE_PRIVATE)
        var accessToken = sharedPrefs.getString(Constants.ACCESS_TOKEN, Constants.EMPTY_STRING)!!
        if (accessToken.isNotEmpty())
            courses_view_model.getCourses(accessToken)
    }

        override fun onResume() {
            super.onResume()
            courses_view_model.coursesLiveData.observe(this, { courses ->
                var coursesList = listOf(
                    Courses("AND 101", "Android", "2", "Native android development", "John Owuor"),
                    Courses("PY 101", "Python", "5", "Backend development", "James Mwai"),
                    Courses("JS 101", "JavaScript", "8", "Frontend development", "Purity Maina"),
                    Courses("IOT 101", "IOT", "9", "IOT for connectivity of network", "Baren"),
                    Courses("UX 101", "UX research", "10", "MAke research before anything", "Joy"),
                )
                var coursesAdapter = courses_recyclerview_adaptervar(coursesList)
                binding.rvCourses.layoutManager = LinearLayoutManager(baseContext)
                binding.rvCourses.adapter = coursesAdapter
                Toast.makeText(this, "fetched ${courses.size} courses", Toast.LENGTH_LONG).show()
            })
            courses_view_model.errorLiveData.observe(this,{ error->
                Toast.makeText(this,error,Toast.LENGTH_LONG).show()
            })
        }
}



