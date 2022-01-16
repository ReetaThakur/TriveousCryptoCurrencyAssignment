package com.reeta.triveouscryptocurrencyassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.reeta.triveouscryptocurrencyassignment.R
import com.reeta.triveouscryptocurrencyassignment.adapter.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

/*
This is our home screen that will contain 2 different types of tab and logout option
*/
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var tab: TabLayout
    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Currency Search"
        tab = findViewById(R.id.tabAll)
        viewPager2 = findViewById(R.id.viewPager)
        setAdapter()
    }

    private fun setAdapter() {
        val pagerAdapter = PagerAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = pagerAdapter
        TabLayoutMediator(tab, viewPager2) { tab, position ->
            if (position == 0) tab.text = "All CryptoCurrency"
            else tab.text = "Favorites CryptoCurrency"
        }.attach()
    }

    // this method will show menu inside our action bar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.logout, menu)
        return true
    }

    // this method perform action that particular menu option
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.idlogout -> {
                Toast.makeText(this, "Logout SuccessFul", Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, SignInActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}