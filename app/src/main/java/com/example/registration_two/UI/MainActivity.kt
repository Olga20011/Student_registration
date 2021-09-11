package com.example.registration_two.UI

import android.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.example.registration_two.Constants
import com.example.registration_two.View_model.User_view_model
import com.example.registration_two.databinding.ActivityMainBinding
import com.example.registration_two.models.RegistrationRequest

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPrefs:SharedPreferences
    val user_view_model:User_view_model by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        clickRegister()
        redirectUser()
        sharedPrefs=getSharedPreferences(Constants.REGISTRATION_PREFS,Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        user_view_model.registrationLiveData.observe(this,{regRespone->
            if(!regRespone.studentId.isNullOrEmpty()){
                Toast.makeText(baseContext,"Registration is successful",Toast.LENGTH_LONG).show()
            }
        })
        user_view_model.errorLiveData.observe(this,{ error->
            Toast.makeText(baseContext,"error",Toast.LENGTH_LONG).show()
            binding.pbRegistration.visibility=View.GONE
        })
    }

    fun setupSpinner() {
        var nationalities= arrayOf("Select nationality","Kenyan","Rwandan","South Sudanese","Ugandan")
        var nationalitiesAdapter= ArrayAdapter(baseContext, R.layout.simple_spinner_dropdown_item,nationalities)
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var nationalityAdapter = null
        binding.spNationality.adapter=nationalityAdapter
    }

    fun clickRegister() {
        var error = false
        binding.btnregister.setOnClickListener {
            var name = binding.etName.text.toString()
            if (name.isEmpty()) {
                error = true
                binding.etName.setError("this field is required")
            }
            var dob = binding.etDOB.text.toString()
            if (dob.isEmpty()) {
                error = true
                binding.etDOB.text.toString()
            }
            var nationality = ""
            if (binding.spNationality.selectedItemPosition != 0) {
                nationality = binding.spNationality.selectedItem.toString()
            }

            var phone = binding.etPhoneNumber.text.toString()
            if (phone.isEmpty()) {
                binding.etPhoneNumber.text.toString()
            }
            var email = binding.etEmail.text.toString()
            if (email.isEmpty()) {
                error = true
                binding.etEmail.text.toString()
            }
            var password = binding.etPassword.text.toString()
            if (password.isEmpty()) {
                error = true
                binding.etPassword.setError("this field is required")
            }
            if (!error) {
                binding.pbRegistration.visibility=View.VISIBLE
                var regRequest = RegistrationRequest(
                    name = name,
                    phoneNumber = phone,
                    email = email,
                    dateOfBirth = dob,
                    nationality = nationality,
                    password = password

                )
                user_view_model.registerStudent(regRequest)


            }
        }



    }
    fun redirectUser(){
        var access_token=sharedPrefs.getString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)!!
        if (access_token.isNotEmpty()&& access_token.isNotBlank()){
            startActivity(Intent(this,CoursesActivity::class.java))
        }else{
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }


}





