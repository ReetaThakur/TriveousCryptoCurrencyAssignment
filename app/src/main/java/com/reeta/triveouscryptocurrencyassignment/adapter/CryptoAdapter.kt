package com.reeta.triveouscryptocurrencyassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reeta.triveouscryptocurrencyassignment.R
import com.reeta.triveouscryptocurrencyassignment.apiResponse.Data
import java.text.DecimalFormat

class CryptoAdapter(var currencyList:ArrayList<Data>):RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    companion object{
        var decimal:DecimalFormat= DecimalFormat("#.##")
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.currency_item_layout,parent,false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val data=currencyList[position]
        holder.setData(data)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    fun filterList(filterList:ArrayList<Data>){
        currencyList=filterList
        notifyDataSetChanged()
    }


    class CryptoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val currencySymbol:TextView=itemView.findViewById(R.id.txtCurrencySymbol)
        val currencyName:TextView=itemView.findViewById(R.id.txtCurrencyName)
        val currencyPrice:TextView=itemView.findViewById(R.id.txtCurrencyPrice)
        fun setData(data: Data){
            currencySymbol.text=data.symbol
            currencyName.text=data.name
            currencyPrice.text="$ ${decimal.format(data.quote.USD.price)}"

        }


    }
}