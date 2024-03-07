package com.example.greendzine_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Set the default selected item
        bottomNavigationView.selectedItemId = R.id.navigation_home


        // Handle bottom navigation item clicks
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // Handle Home tab click
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragmentContainer,
                        HomeFragment()
                    ).commit()
                    true
                }
                R.id.navigation_user -> {
                    // Handle User tab click
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragmentContainer,
                        UserFragment()
                    ).commit()
                    true
                }
                else -> false
            }
        }

        // Set HomeFragment as default on app start
        supportFragmentManager.beginTransaction().replace(
            R.id.fragmentContainer,
            HomeFragment()
        ).commit()
    }
}
