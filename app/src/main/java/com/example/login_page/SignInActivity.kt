package com.example.login_page

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.login_page.databinding.ActivityMainBinding
import com.example.login_page.databinding.ActivitySignInPageBinding

class SignInActivity:AppCompatActivity() {
private lateinit var binding: ActivitySignInPageBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backBtn.setOnClickListener{
            val intent=Intent(this@SignInActivity,MainActivity::class.java);
            startActivity(intent)
        }
        binding.signUpTxt.setOnClickListener {
            val intent=Intent(this@SignInActivity,SignUp_page::class.java);
            startActivity(intent)
        }
//        setContentView(R.layout.signup_activity)
//        val signInButton =findViewById<Button>(R.id.in_btn)
//        signInButton.setOnClickListener {
//            val intent = Intent(this@SignInActivity,MainActivity::class.java)
////            startActivity(intent)
//        }
        println("this@SignUpActivity Activity LifeCycle-----OnCreate")
    }

    override fun onStart() {
        super.onStart()
        println("this@SignUpActivity Activity LifeCycle-----OnStart")
    }

    override fun onPause() {
        super.onPause()
        println("this@SignUpActivity Activity LifeCycle-----OnPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("this@SignUpActivity Activity LifeCycle-----onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        println("this@SignUpActivity Activity LifeCycle-----onRestory")
    }

    override fun onResume() {
        super.onResume()
        println("this@SignUpActivity Activity LifeCycle-----onResume")
    }

    override fun onStop() {
        super.onStop()

        println("this@SignUpActivity Activity LifeCycle-----onStop")
    }
}