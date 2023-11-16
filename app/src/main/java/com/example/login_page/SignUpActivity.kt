package com.example.login_page

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.core.widget.addTextChangedListener

import com.example.login_page.databinding.SignupActivityBinding

class SignUpActivity : AppCompatActivity(),TextWatcher {
    private lateinit var binding:SignupActivityBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=SignupActivityBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            val sharedPref =  getSharedPreferences("Local", Context.MODE_PRIVATE)
            val intent=Intent(applicationContext,HomeActivity::class.java);
            val name:String=binding.nameEditText.text.toString()
//            val password=binding.passwordEditText.text.toString()
            val email=binding.emailEditText.text.toString()
            val editor = sharedPref.edit()
            editor.putString("Newname", name)
            editor.putString("NewmailId",email)
            editor.apply()

            startActivity(intent)
        }

        binding.backBtn.setOnClickListener{

            finish();
        }
        binding.signInBtn.setOnClickListener {
            val intent=Intent(this@SignUpActivity,SignInActivity::class.java);
            startActivity(intent)
        }
        binding.nameEditText.addTextChangedListener (this)
        binding.emailEditText.addTextChangedListener (this)
        binding.passwordEditText.addTextChangedListener (this)
        binding.repetPasswordEditText.addTextChangedListener (this)
        //setContentView(R.layout.activity_sign_in_page)
    }


private fun vaildRepeatPassword(input: String){
    val password=binding.passwordEditText.text.toString();
    val entered=input
    if(entered.isEmpty()){
        binding.repetPasswordEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.login_background))
        binding.repetPasswordContainer.helperText="required"

    }else if(!entered.equals(password)){
        binding.repetPasswordEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.red_back))
        binding.repetPasswordContainer.helperText="Not Same"

    }else{
        binding.repetPasswordEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.green_back))
        binding.repetPasswordContainer.helperText=null

    }
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
            return "Must Contain 1 Numericals [0-9]"
        }

        return null
    }

    private fun vaildPassword(s: String){
        var valid=validPassword(s)

        if(s.isEmpty()){
            binding.passwordEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.login_background))
            binding.passwordContainer.helperText= valid

        }else if(valid!=null) {
            binding.passwordEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.red_back))
            binding.passwordContainer.helperText = valid

        }else {
            binding.passwordEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.green_back))
            binding.passwordContainer.helperText = null

        }

    }

    private fun vaildEmail( s: String)
    {
        val emailText=s;
        if(s.length==0){
            binding.emailEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.login_background));
            binding.emailContainer.helperText= "required"
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            binding.emailEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.red_back));
            binding.emailContainer.helperText= "Invalid Email"
        }else{
            binding.emailEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.green_back));

            binding.emailContainer.helperText=null;

        }

    }
    private fun vaildName(s: String){
    if(s.isEmpty()){
        binding.nameEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.login_background))
        binding.nameContainer.helperText="required"

    }else if(s.length<8){
        binding.nameEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.red_back))
        binding.nameContainer.helperText =  "Minimum 8 Character Name"

    }else{
        binding.nameEditText.setBackgroundDrawable(resources.getDrawable(R.drawable.green_back))
        binding.nameContainer.helperText =  null

    }
}



    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        vaildName(binding.nameEditText.text.toString())
        vaildEmail(binding.emailEditText.text.toString())
        vaildPassword(binding.passwordEditText.text.toString())
        vaildRepeatPassword(binding.repetPasswordEditText.text.toString())
        var flag1=binding.nameContainer.helperText;
        var flag2=binding.emailContainer.helperText;
        var flag3=binding.passwordContainer.helperText;
        var flag4=binding.repetPasswordContainer.helperText;
        if(flag1==null&&flag2==null&&flag3==null&&flag4==null){
            //true
            binding.signUpButton.backgroundTintList= ColorStateList.valueOf(getColor(R.color.lightblue))
            binding.signUpButton.isEnabled=true
        }else{
            //false
            binding.signUpButton.backgroundTintList= ColorStateList.valueOf(getColor(R.color.ash))
            binding.signUpButton.isEnabled=false
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

}