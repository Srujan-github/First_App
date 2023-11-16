package com.example.login_page

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.login_page.databinding.ActivityHomeBinding
import com.example.login_page.databinding.NavHeaderBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeFragment = HomeFragment()
        val messagesFragment = MessagesFragment()
        val settingsFragment = SettingsFragment()
        supportFragmentManager.beginTransaction().apply {

            replace(R.id.flvfragements, homeFragment)

            commit()
        }
        val headerView: View = binding.navView.getHeaderView(0)
        val headerBinding: NavHeaderBinding = NavHeaderBinding.bind(headerView)
        val sharedPref =  getSharedPreferences("Local", Context.MODE_PRIVATE)
        headerBinding.userName.text = sharedPref.getString("Newname", "Sai Surjan")
        headerBinding.userMailId.text=sharedPref.getString("NewmailId","saisrujan@1gmail.com")
        headerBinding.ProfileImg.setImageURI(Uri.parse(sharedPref.getString("image","iVBORw0KGgoAAAANSUhEUgAAAAUA" +
                "AAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO" +
                "9TXL0Y4OHwAAAABJRU5ErkJggg==")))
        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@HomeActivity,
                drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {

                when (it.itemId) {
                    R.id.home -> {
                        supportFragmentManager.beginTransaction().apply {

                            replace(R.id.flvfragements, homeFragment)
                            repeat(supportFragmentManager.backStackEntryCount) {
                                supportFragmentManager.popBackStack()
                            }
                            commit()
                        }
                        Toast.makeText(
                            this@HomeActivity,
                            "this is Home Fragment",
                            Toast.LENGTH_SHORT
                        ).show()

                        drawerLayout.close()

                    }

                    R.id.message -> {
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.flvfragements, messagesFragment)
                            addToBackStack(null)
                            commit()
                        }
                        Toast.makeText(
                            this@HomeActivity,
                            "this is Messages Fragment",
                            Toast.LENGTH_SHORT
                        ).show()
                        drawerLayout.close()
                    }

                    R.id.setting -> {

                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.flvfragements, settingsFragment)
                            addToBackStack(null)
                            commit()
                        }
                        Toast.makeText(
                            this@HomeActivity,
                            "this is Settings Fragment",
                            Toast.LENGTH_SHORT
                        ).show()
                        drawerLayout.close()
                    }

                    R.id.looOut -> {

                        Toast.makeText(this@HomeActivity, "You are logged out", Toast.LENGTH_SHORT)
                            .show()
                        val editor=sharedPref.edit()
                        editor.clear()
                        editor.apply()
                        finish()
                    }
                }
                true
            }

        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        val headerView: View = binding.navView.getHeaderView(0)
//        val headerBinding: NavHeaderBinding = NavHeaderBinding.bind(headerView)
//        headerBinding.ProfileImg.setImageURI(data?.data)
//
//
//    }


}