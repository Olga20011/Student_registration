package com.example.registration_two.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.registration_two.View_model.login_view_model
import com.example.registration_two.databinding.ActivityLoginBinding
import com.example.registration_two.models.LoginRequest

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val  login_view_model: login_view_model by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Login()
    }
        override fun onResume() {
            super.onResume()
            login_view_model.loginLiveData.observe(this,{LoginRegister->
                if(!LoginRegister.studentId.isNullOrEmpty()){
                    Toast.makeText(baseContext,"Login is successful", Toast.LENGTH_LONG).show()
                }
            })
            login_view_model.erroLiveData.observe(this,{ error->
                Toast.makeText(baseContext,"error", Toast.LENGTH_LONG).show()
            })
            binding.pbLogin.visibility= View.GONE
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
                    password =password,
                )
                login_view_model.loginStudent(LoginRegister)

            }
        }
    }
}