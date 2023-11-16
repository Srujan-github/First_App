package com.example.login_page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LanchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref =  getSharedPreferences("Local", Context.MODE_PRIVATE)
        val name = sharedPref.getString("Newname", null)
        val email = sharedPref.getString("Newmail", null)
        if(name.isNullOrEmpty()&&email.isNullOrEmpty()){
           val intent=Intent(this@LanchActivity,SignUpActivity ::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent=Intent(this@LanchActivity,HomeActivity ::class.java)
            startActivity(intent)
            finish()
        }

    }
}