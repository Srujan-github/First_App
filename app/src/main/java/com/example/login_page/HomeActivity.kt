package com.example.login_page

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.login_page.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeFragment = HomeFragment()
        val messagesFragment = MessagesFragment()
        val settingsFragment=SettingsFragment()

        binding.apply {
           toggle=ActionBarDrawerToggle(this@HomeActivity,drawerLayout,R.string.open,R.string.close)
           drawerLayout.addDrawerListener(toggle)
           toggle.syncState()

           supportActionBar?.setDisplayHomeAsUpEnabled(true)

           navView.setNavigationItemSelectedListener {

               when(it.itemId){
                   R.id.home -> {
                       supportFragmentManager.beginTransaction().apply {
                           replace(R.id.flvfragements,homeFragment)
                           commit()
                       }
                       Toast.makeText(this@HomeActivity,"this is Home Fragment",Toast.LENGTH_SHORT).show()
                 drawerLayout.close()

                   }
                   R.id.message -> {
                       supportFragmentManager.beginTransaction().apply {
                           replace(R.id.flvfragements,messagesFragment)
                           commit()
                       }
                       Toast.makeText(this@HomeActivity,"this is Messages Fragment",Toast.LENGTH_SHORT).show()
                       drawerLayout.close()
                   }
                   R.id.setting -> {
                       supportFragmentManager.beginTransaction().apply {
                           replace(R.id.flvfragements,settingsFragment)
                           commit()
                       }
                       Toast.makeText(this@HomeActivity,"this is Settings Fragment",Toast.LENGTH_SHORT).show()
                       drawerLayout.close()
                   }
                   R.id.looOut ->{

                       Toast.makeText(this@HomeActivity,"You are logged out",Toast.LENGTH_SHORT).show()
                       finish()
                   }
               }
            true
           }

       }



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}