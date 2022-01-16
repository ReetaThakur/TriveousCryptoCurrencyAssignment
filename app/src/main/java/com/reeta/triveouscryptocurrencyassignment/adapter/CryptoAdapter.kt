package com.reeta.triveouscryptocurrencyassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reeta.triveouscryptocurrencyassignment.R
import com.reeta.triveouscryptocurrencyassignment.apiResponse.Data
import com.reeta.triveouscryptocurrencyassignment.ui.AddToFavorites
import java.text.DecimalFormat

//This is Adapter for initialization purpose, it will initialize list those data comes from Api
class CryptoAdapter(var currencyList: List<Data>, val addToFavorites: AddToFavorites) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    //This use for formatting price for only 2 decimal places
    companion object {
        var decimal: DecimalFormat = DecimalFormat("#.##")
    }

    // onCreateViewHolder use for creating the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item_layout, parent, false)
        return CryptoViewHolder(view, addToFavorites)
    }

    // onBindViewHolder use for binding our data into views
    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val data = currencyList[position]
        holder.setData(data)
    }

    //getItemCount will return how many item in the list
    override fun getItemCount(): Int {
        return currencyList.size
    }

    //this method for search item in search box
    fun filterList(filterList: ArrayList<Data>) {
        currencyList = filterList
        notifyDataSetChanged()
    }


    //viewHolder will take all reference that our view hold like TextView, Button
    class CryptoViewHolder(itemView: View, var addToFavorites: AddToFavorites) :
        RecyclerView.ViewHolder(itemView) {
        val currencySymbol: TextView = itemView.findViewById(R.id.txtCurrencySymbol)
        val currencyName: TextView = itemView.findViewById(R.id.txtCurrencyName)
        val currencyPrice: TextView = itemView.findViewById(R.id.txtCurrencyPrice)
        val addFavorites: Button = itemView.findViewById(R.id.btnAddToFavorites)
        fun setData(data: Data) {
            currencySymbol.text = data.symbol
            currencyName.text = data.name
            currencyPrice.text = "$ ${decimal.format(data.quote.USD.price)}"
            addFavorites.setOnClickListener {
                addToFavorites.addToFavorites(data)
            }
        }
    }
}