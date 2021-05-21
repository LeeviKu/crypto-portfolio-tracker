package com.example.cryptotracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

/**
 * Activity class for portfolio
 */
class Portfolio : AppCompatActivity() {
    var holdings: ArrayList<Holding> = arrayListOf()
    lateinit var listView: RecyclerView
    lateinit var itemsAdapter: PortfolioAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.portfolio)
        listView = findViewById(R.id.portfolio)
        var sharedPreferences = getSharedPreferences("holdings", MODE_PRIVATE)
        // gets all saved holdings from sharedPreferences
        sharedPreferences.all.forEach() {
            var holdingArray = it.toString().split("=")
            var amountAndPriceArray = holdingArray[1].split(";")
            val holding = Holding(holdingArray[0], amountAndPriceArray[0], amountAndPriceArray[1])
            holdings.add(holding)
        }
        // put holdings into a recycleView
        itemsAdapter = PortfolioAdapter(holdings, this)
        listView.adapter = itemsAdapter
        (listView.adapter as PortfolioAdapter).notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    // on click function for change holding button
    fun openAddHoldingChange(view: View) {
        val intent = Intent(this, ChooseHoldingToChange::class.java)
        startActivity(intent)
    }
}