package com.example.login_page

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

import com.example.login_page.databinding.ActivitySignInPageBinding

  class SignInActivity:AppCompatActivity(),TextWatcher{
private lateinit var binding: ActivitySignInPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
         var editName=binding.nameEditText
        var  editNCon=binding.nameContainer
        var editPass=binding.passwordEditText
        var  editPCon=binding.passwordContainer
        binding.backBtn.setOnClickListener{
            finish();
        }
        binding.signUpTxt.setOnClickListener {
            val intent=Intent(this@SignInActivity,SignUpActivity::class.java);
            startActivity(intent)
        }

        binding.signInButton.setOnClickListener {
            val name:String=editName.text.toString()
            val password=editPass.text.toString()
            val intent=Intent(applicationContext,HomeActivity::class.java)
            intent.putExtra("Name",name)
            intent.putExtra("Password",password)
            startActivity(intent)
        }
        editPass.addTextChangedListener(this)
        editName.addTextChangedListener(this)

        println("this@SignUpActivity Activity LifeCycle-----OnCreate")
    }
    private fun validPassword(input: String?): String?
    {
        val passwordText = input.toString();
        if(passwordText.length==0) {
        return "required"
        }
        else if(passwordText.length < 8)
        {
            return "Minimum 8 Character Password"
        }
       else if(!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }
        else  if(!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case Character"
        }
        else if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }
        else if(!passwordText.matches(".*[0-9].*".toRegex()))
        {
            return "Must Contain 2 Numericals [0-9]"
        }

        return null
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

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

      }


      @SuppressLint("UseCompatLoadingForDrawables")
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
          var editName=binding.nameEditText
          var  editNCon=binding.nameContainer
          var editPass=binding.passwordEditText
          var  editPCon=binding.passwordContainer
          var password=editPass.text.toString()

          var valid=validPassword(password)

          if(password.isEmpty()){
              editPass.setBackgroundDrawable(resources.getDrawable(R.drawable.login_background))
              editPCon.helperText=valid

          }else if(valid!=null) {
              editPass.setBackgroundDrawable(resources.getDrawable(R.drawable.red_back))
              editPCon.helperText = valid

          }else {
              editPass.setBackgroundDrawable(resources.getDrawable(R.drawable.green_back))
              editPCon.helperText = null

          }
          var name=editName.text.toString()
          if(name.isEmpty()){
              editName.setBackgroundDrawable(resources.getDrawable(R.drawable.login_background))
              editNCon.helperText="required"

          }else if(name.length<8){
              editName.setBackgroundDrawable(resources.getDrawable(R.drawable.red_back))
              editNCon.helperText =  "Minimum 8 Character Name"

          }else{
              editName.setBackgroundDrawable(resources.getDrawable(R.drawable.green_back))
              editNCon.helperText =  null
          }

          if( editNCon.helperText==null&&editPCon.helperText==null){
              //true
              binding.signInButton.backgroundTintList=ColorStateList.valueOf(getColor(R.color.lightblue))
              binding.signInButton.isEnabled=true
          }else{
              //false
              binding.signInButton.backgroundTintList=ColorStateList.valueOf(getColor(R.color.ash))
              binding.signInButton.isEnabled=false
          }

      }

      override fun afterTextChanged(s: Editable?) {

      }


  }






