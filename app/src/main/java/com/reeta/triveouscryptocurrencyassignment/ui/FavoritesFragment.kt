package com.reeta.triveouscryptocurrencyassignment.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.reeta.triveouscryptocurrencyassignment.R
import com.reeta.triveouscryptocurrencyassignment.adapter.CryptoAdapter
import com.reeta.triveouscryptocurrencyassignment.adapter.FavoriteAdapter
import com.reeta.triveouscryptocurrencyassignment.database.AddCurrency
import com.reeta.triveouscryptocurrencyassignment.database.CurrencyDao
import com.reeta.triveouscryptocurrencyassignment.database.RoomDatabaseCurrency
import com.reeta.triveouscryptocurrencyassignment.repository.Repository
import com.reeta.triveouscryptocurrencyassignment.viewModel.CurrencyViewModel
import com.reeta.triveouscryptocurrencyassignment.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_all_crypto_currency.*
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
This Fragment will show all favorites crypto currencies that you add into first fragment
and if you want to remove those currency you can do it.
 */
class FavoritesFragment : Fragment(R.layout.fragment_favorites), RemoveFromFavorites {

    lateinit var favoriteAdapter: FavoriteAdapter
    lateinit var viewModel: CurrencyViewModel
    lateinit var favoriteList: ArrayList<AddCurrency>
    lateinit var repository: Repository
    lateinit var currecyDao: CurrencyDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currecyDao = context?.let { RoomDatabaseCurrency.getDatabaseObject(it).getDao() }!!
        repository = Repository(currecyDao)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(repository))
            .get(CurrencyViewModel::class.java)

        favoriteList = ArrayList()
        setRecyclerView()

        // here we are observing data
        viewModel.getCurrencyData().observe(viewLifecycleOwner, {
            favoriteAdapter.setListData(ArrayList(it))
            favoriteAdapter.notifyDataSetChanged()
        })

    }

    //setting recylcerView
    private fun setRecyclerView() {
        favoriteAdapter = FavoriteAdapter(favoriteList, this)
        val linearLayoutManager = LinearLayoutManager(context)
        favoriteRecyclerview.apply {
            adapter = favoriteAdapter
            layoutManager = linearLayoutManager
        }
    }

    // this method will remove that particular crypto currency from database
    override fun removeCurrency(currency: AddCurrency) {
        val builder = context?.let { androidx.appcompat.app.AlertDialog.Builder(it) }
        builder?.setTitle("Delete")
        builder?.setMessage("Do you really want to delete the currency ?")
        builder?.setCancelable(false)
        builder?.setPositiveButton(
            "Yes"
        ) { dialogInterface, i ->

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.deleteCurrencyData(currency)
                CoroutineScope(Dispatchers.Main).launch {
                    favoriteAdapter.notifyDataSetChanged()
                }

            }
        }
        builder?.setNegativeButton(
            "No"
        ) { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        builder?.create()?.show()
    }

}

