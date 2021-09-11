package com.example.registration_two.UI

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.registration_two.Constants
import com.example.registration_two.View_model.login_view_model
import com.example.registration_two.databinding.ActivityLoginBinding
import com.example.registration_two.models.LoginRequest
import com.example.registration_two.models.LoginResponse

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedprefs:SharedPreferences
    val  login_view_model: login_view_model by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedprefs=getSharedPreferences(Constants.REGISTRATION_PREFS,Context.MODE_PRIVATE)
        Login()
//        observeloginResponse()

    }
        override fun onResume() {
            super.onResume()
            login_view_model.loginLiveData.observe(this,{LoginRegister->
                var access_token=LoginRegister.accessToken
                access_token="Bearer $access_token"
                var editor=sharedprefs.edit()
                editor.putString(Constants.ACCESS_TOKEN,access_token)
                editor.putString(Constants.STUDENT_ID,LoginRegister.studentId)
                editor.apply()
                Toast.makeText(baseContext,LoginRegister.message,Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext,CoursesActivity::class.java))

                binding.pbLogin.visibility=View.GONE

            })
            login_view_model.erroLiveData.observe(this,{error->
                Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                binding.pbLogin.visibility= View.GONE
            })
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
//    fun observeloginResponse(){
//        login_view_model.loginLiveData.observe(this,{LoginResponse->
//            var access_token=LoginResponse.accessToken
//            access_token="Bearer $access_token"
//            var editor =sharedprefs.edit()
//            editor.putString(Constants.ACCESS_TOKEN,access_token)
//            editor.putString("STUDENT_ID",LoginRegister.student_id)
//            editor.apply()
//
//            Toast.makeText(baseContext,LoginResponse.message,Toast.LENGTH_LONG).show()
//            startActivity(Intent(baseContext,CoursesActivity::class.java))
//
//        })
//        login_view_model.loginLiveData.observe(this,{error->
//            Toast.makeText(baseContext,error.message,Toast.LENGTH_LONG).show()
//        })
//    }
