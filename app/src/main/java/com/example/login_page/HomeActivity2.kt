package com.example.login_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_page.databinding.ActivityHome2Binding

class HomeActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityHome2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHome2Binding.inflate(layoutInflater)
        val intent = intent
        val name = intent.getStringExtra("Name")
        val pass=intent.getStringExtra("Password")
        val email=intent.getStringExtra("Email")
        binding.showNameTextView.setText(name)
        binding.showPasswordTV.setText(pass)
        binding.showEmailTV.setText(email)
        setContentView(binding.root)

    }
}