package com.reeta.triveouscryptocurrencyassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var tab: TabLayout
    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tab=findViewById(R.id.tabAll)
        viewPager2=findViewById(R.id.viewPager)
        setAdapter()
    }

    private fun setAdapter() {
        val pagerAdapter=PagerAdapter(supportFragmentManager,lifecycle)
        viewPager2.adapter=pagerAdapter
        TabLayoutMediator(tab, viewPager2) { tab, position ->
            if (position==0) tab.text="All CryptoCurrency"
            else tab.text="Favorites CryptoCurrency"
        }.attach()
    }
}