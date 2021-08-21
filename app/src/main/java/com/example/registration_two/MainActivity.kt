package com.example.registration_two

import android.R
import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.registration_two.API.ApiClient
import com.example.registration_two.API.Api_interface
import com.example.registration_two.databinding.ActivityMainBinding
import com.example.registration_two.models.RegistrationRequest
import com.example.registration_two.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        clickRegister()
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

                var retrofit = ApiClient.buildApiClient(Api_interface::class.java)
                var request = retrofit.registerStudent(regRequest)
                request.enqueue(object : Callback<RegistrationResponse?> {
                    override fun onResponse(
                        call: Call<RegistrationResponse?>,
                        response: Response<RegistrationResponse?>
                    ) {
                        if (response.isSuccessful) {
                            binding.pbRegistration.visibility=View.GONE
                            Toast.makeText(
                                baseContext,
                                "Registration successful",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                baseContext,
                                response.errorBody()?.string(),
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }

                    override fun onFailure(call: Call<RegistrationResponse?>, t: Throwable) {
                        binding.pbRegistration.visibility=View.GONE
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

                    }
                })

            }
        }



    }
}
//data class Person(var name:String,var dob:String,var idno:String,var nation:String,var phone:String,var email:String)





