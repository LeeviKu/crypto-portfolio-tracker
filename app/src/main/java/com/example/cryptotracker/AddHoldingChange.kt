package com.example.cryptotracker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.google.android.material.textfield.TextInputEditText


class AddHoldingChange : AppCompatActivity() {
    lateinit var name: TextView
    lateinit var switch: Switch
    lateinit var amount: TextInputEditText
    lateinit var price: TextInputEditText
    lateinit var addHoldingButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_holding_change)
        name = findViewById(R.id.name)
        switch = findViewById(R.id.sellOrBuySwitch)
        amount = findViewById(R.id.amount)
        price = findViewById(R.id.price)
        addHoldingButton = findViewById(R.id.addHoldingButton)
        name.text = intent.getStringExtra("name")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ChooseHoldingToChange::class.java)
        startActivity(intent)
    }

    fun addHolding(v: View) {
        var sharedPreferences = getSharedPreferences("holdings", MODE_PRIVATE)
        var amountOfCoins = amount.text.toString()
        var pricePerCoin = price.text.toString()
        var currentHoldingAmount = 0.0
        var currentAveragePrice = 0.0
        var isSell = switch.isChecked
        var currentHoldings = sharedPreferences.getString(name.text.toString(),"")
        if (currentHoldings != "" && currentHoldings != null) {
            val holdingsArray = currentHoldings.split(";")
            currentHoldingAmount = holdingsArray[0].toDouble()
            currentAveragePrice = holdingsArray[1].toDouble()
        }
        if (isSell) {
            sharedPreferences.edit {
                if (currentHoldingAmount - amountOfCoins.toDouble() >= 0) {
                    var newAmount = currentHoldingAmount - amountOfCoins.toDouble()
                    if (newAmount == 0.0) {
                        this.remove(name.text.toString())
                    } else {
                        this.putString(name.text.toString(), "${newAmount};${currentAveragePrice}")
                    }
                }
            }
        } else {
            sharedPreferences.edit {
                var newAmount = currentHoldingAmount + amountOfCoins.toDouble()
                var newPricePerCoin = ((currentAveragePrice * currentHoldingAmount) +
                        (pricePerCoin.toDouble() * amountOfCoins.toDouble())) /
                        (currentHoldingAmount + amountOfCoins.toDouble())
                this.putString(name.text.toString(), "${newAmount};${newPricePerCoin}")
            }
        }
        val intent = Intent(this, Portfolio::class.java)
        startActivity(intent)
    }
}