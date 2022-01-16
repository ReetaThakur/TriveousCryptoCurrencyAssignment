package com.reeta.triveouscryptocurrencyassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.reeta.triveouscryptocurrencyassignment.R
import com.reeta.triveouscryptocurrencyassignment.apiResponse.Data
import com.reeta.triveouscryptocurrencyassignment.database.AddCurrency
import com.reeta.triveouscryptocurrencyassignment.ui.RemoveFromFavorites
import java.text.DecimalFormat

//This adapter use for add favorites currency, and initiliaze our data
class FavoriteAdapter(var favoriteList:List<AddCurrency>,
                      var removeCurrency: RemoveFromFavorites)
    :RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    fun setListData(currency:List<AddCurrency>){
        this.favoriteList=currency
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.favorites_layout,parent,false)
        return FavoriteViewHolder(view,removeCurrency)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val list=favoriteList[position]
        holder.setData(list)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }


    class FavoriteViewHolder(itemView:View,var removeCurrency: RemoveFromFavorites):RecyclerView.ViewHolder(itemView){
        val currencyName: TextView =itemView.findViewById(R.id.txtFavCurrencyName)
        val currencyPrice: TextView =itemView.findViewById(R.id.txtFavCurrencyPrice)
        val remove: Button =itemView.findViewById(R.id.btnRemoveFromFavorites)

        fun setData(currency: AddCurrency){
            currencyName.text=currency.currencyName
            currencyPrice.text="$ ${currency.currencyPrice}"
            remove.setOnClickListener {
              removeCurrency.removeCurrency(currency)
            }
        }
    }
}