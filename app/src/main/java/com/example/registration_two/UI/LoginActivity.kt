package com.example.registration_two.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.registration_two.API.ApiClient
import com.example.registration_two.API.Api_interface
import com.example.registration_two.View_model.Login_view_model
import com.example.registration_two.databinding.ActivityLoginBinding
import com.example.registration_two.models.LoginRequest
import com.example.registration_two.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val LoginViewModel=Login_view_model by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun Login(){
        var error=false
        binding.btnLogin.setOnClickListener {
            var email=binding.etEmail.text.toString()
            if(email.isEmpty()){
                error=true
                binding.etEmail.setError("This field is required")
            }

            var password=binding.etpassword.text.toString()
            if (password.isEmpty()){
                error=true
                binding.etpassword.setError("This field is required")
            }
            if(! error){
                binding.pbLogin.visibility=View.VISIBLE
                var LoginRegister=LoginRequest(
                    email =email,
                    password =password
                )

            }
        }
    }
}