package com.reeta.triveouscryptocurrencyassignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.reeta.triveouscryptocurrencyassignment.ui.AllCryptoCurrencyFragment
import com.reeta.triveouscryptocurrencyassignment.ui.FavoritesFragment

//This pagerAdapter will use for changing fragment dynamically
class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    //This method will return fragment as we swip or tab on to fragments
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return AllCryptoCurrencyFragment()
            1 -> return FavoritesFragment()
        }
        return AllCryptoCurrencyFragment()
    }
}