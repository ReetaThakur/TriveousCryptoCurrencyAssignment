package com.reeta.triveouscryptocurrencyassignment.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.reeta.triveouscryptocurrencyassignment.R;
import com.reeta.triveouscryptocurrencyassignment.apiResponse.Data;

import java.util.ArrayList;
import java.util.Locale;

public class PracticeActivity extends AppCompatActivity {

    ArrayList<Data> currencyList=new ArrayList<>();
    private EditText searchEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void filterCurrency(String currency){
        ArrayList<Data> filterList=new ArrayList<>();
        for (Data data:currencyList){
            if (data.getName().toLowerCase().contains(currency.toLowerCase())){
                filterList.add(data);
            }
        }
        if (filterList.isEmpty()){
            Toast.makeText(this,"No currency found for searched query",Toast.LENGTH_LONG).show();
        }else{

        }
    }
}