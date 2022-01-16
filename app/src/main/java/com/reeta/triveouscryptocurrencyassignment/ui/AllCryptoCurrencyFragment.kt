package com.reeta.triveouscryptocurrencyassignment.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.reeta.triveouscryptocurrencyassignment.R
import com.reeta.triveouscryptocurrencyassignment.adapter.CryptoAdapter
import com.reeta.triveouscryptocurrencyassignment.apiResponse.*
import com.reeta.triveouscryptocurrencyassignment.database.AddCurrency
import com.reeta.triveouscryptocurrencyassignment.database.CurrencyDao
import com.reeta.triveouscryptocurrencyassignment.database.RoomDatabaseCurrency
import com.reeta.triveouscryptocurrencyassignment.repository.Repository
import com.reeta.triveouscryptocurrencyassignment.viewModel.CurrencyViewModel
import com.reeta.triveouscryptocurrencyassignment.viewModel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_all_crypto_currency.*
import java.text.DecimalFormat

/*
This Fragment will show all different types of crypto currencies and if you want to
search your favorite crypto currency you can search it.
 */
@AndroidEntryPoint
class AllCryptoCurrencyFragment : Fragment(R.layout.fragment_all_crypto_currency), AddToFavorites {

    var currencyList = mutableListOf<Data>()
    lateinit var currencyAdapter: CryptoAdapter
    val viewModel: CurrencyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                filterCurrency(s.toString())
            }
        })
    }

    //This use use for formatting our price only 2 decimal places.
    companion object {
        var decimal: DecimalFormat = DecimalFormat("#.##")
    }

    // This use for desired search result
    private fun filterCurrency(currency: String) {
        val filterList = ArrayList<Data>()
        for (data in currencyList) {
            if (data.name.toLowerCase().contains(currency.toLowerCase())) {
                filterList.add(data)
            }
        }
        if (filterList.isEmpty()) {
            Toast.makeText(context, "No currency found for searched query", Toast.LENGTH_LONG)
                .show()
        } else {
            currencyAdapter.filterList(filterList)
        }
    }

    /*here our data will observe by observer and if it will successful then add all desired
      to our currencyList for showing into screen
     */
    private fun setData() {
        progressBar.visibility = View.VISIBLE
        viewModel.getDataFromAPI().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                Resource.Status.SUCCESS -> {
                    it.data?.data.let {
                        if (it != null) {
                            currencyList.addAll(it)
                            progressBar.visibility = View.GONE
                            setRecyclerView()
                        }
                    }
                }
            }
        })
    }

    //here we are setting recycler view
    private fun setRecyclerView() {
        currencyAdapter = CryptoAdapter(currencyList, this)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.apply {
            adapter = currencyAdapter
            layoutManager = linearLayoutManager
        }
    }

    // this method will add our favorite crypto currency into database
    override fun addToFavorites(data: Data) {
        var currency = AddCurrency(data.name, data.symbol, decimal.format(data.quote.USD.price))
        viewModel.insertCurrencyData(currency)
        Toast.makeText(context, "Currency add in favorites", Toast.LENGTH_SHORT).show()
    }
}
