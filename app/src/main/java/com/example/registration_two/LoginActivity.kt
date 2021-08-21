package com.example.registration_two

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.registration_two.API.ApiClient
import com.example.registration_two.API.Api_interface
import com.example.registration_two.databinding.ActivityLoginBinding
import com.example.registration_two.models.LoginRequest
import com.example.registration_two.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
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
                var regRegister=LoginRequest(
                    email =email,
                    password =password
                )
                var retrofit=ApiClient.buildApiClient(Api_interface::class.java)
                var request=retrofit.loginStudent(regRegister)
                request.enqueue(object : Callback<RegistrationResponse?> {
                    override fun onResponse(
                        call: Call<RegistrationResponse?>,
                        response: Response<RegistrationResponse?>
                    ) {
                        binding.pbLogin.visibility= View.VISIBLE
                        if (response.isSuccessful){
                            Toast.makeText(baseContext, "Login is successful", Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                        }

                    }

                    override fun onFailure(call: Call<RegistrationResponse?>, t: Throwable) {
                        binding.pbLogin.visibility=View.GONE
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                    }


                })
            }
        }
    }
}