package com.example.login_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.login_page.databinding.SignupActivityBinding

class SignUp_page : AppCompatActivity() {
    private lateinit var binding:SignupActivityBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=SignupActivityBinding.inflate(layoutInflater);
        setContentView(binding.root)
        binding.backBtn.setOnClickListener{
            val intent=Intent(this@SignUp_page,MainActivity::class.java);
        startActivity(intent)
        }
        binding.signInBtn.setOnClickListener {
            val intent=Intent(this@SignUp_page,SignInActivity::class.java);
            startActivity(intent)
        }
        //setContentView(R.layout.activity_sign_in_page)
    }
}