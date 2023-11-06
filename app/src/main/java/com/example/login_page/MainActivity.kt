package com.example.login_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_page.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
 private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this@MainActivity,SignUp_page::class.java)
            startActivity(intent)
            print("Hello")
        }
        binding.signInTxt.setOnClickListener {
            val intent=Intent(this@MainActivity,SignInActivity::class.java)
            startActivity(intent)
        }

//        val btn= findViewById<Button>(R.id.sign_Up_btn)
//        btn.setOnClickListener {
//            val intent = Intent(this@MainActivity,SignUpActivity::class.java)
//            startActivity(intent)
//        }
        println("this@MainActivity Activity LifeCycle-----OnCreate")
    }

    override fun onStart() {
        super.onStart()
        println("this@MainActivity Activity LifeCycle-----OnStart")
    }

    override fun onPause() {
        super.onPause()
        println("this@MainActivity Activity LifeCycle-----OnPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("this@MainActivity Activity LifeCycle-----onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        println("this@MainActivity Activity LifeCycle-----onRestory")
    }

    override fun onResume() {
        super.onResume()
        println("this@MainActivity Activity LifeCycle-----onResume")
    }

    override fun onStop() {
        super.onStop()

        println("this@MainActivity Activity LifeCycle-----onStop")

    }
}