package com.example.cryptotracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.commons.io.IOUtils
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
        lateinit var listView: ListView
        lateinit var itemsAdapter: CustomAdapter
        var pageNumber = 0
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            listView = findViewById(R.id.listView)
            listView.isScrollingCacheEnabled = false
            //itemsAdapter = ArrayAdapter<String>(this, R.layout.item, R.id.myTextView, ArrayList<String>())
            Log.d("asd", "asd")
            val url = URL("https://api.coinranking.com/v2/coins?limit=100")
            urlToCoins(url) {
                itemsAdapter = CustomAdapter(this, it as ArrayList<CoinX>)
                listView.adapter = itemsAdapter
            }
            //urlToCoins(url) {
              //  it?.forEach {
                 //   itemsAdapter.add("${it.name} ${it.price}")
               // }
             //   itemsAdapter.notifyDataSetChanged()
           // }
        }

        fun urlToCoins(url : URL, callback: (coins: List<CoinX>?) -> Unit) {
            thread {
                val result = url.downloadUrl()
                Log.d("asd", result)
                val mp = jacksonObjectMapper()
                val coin = mp.readValue<Coin>(result)
                runOnUiThread() {
                    callback(coin.data?.coins)
                }
            }
        }

        fun nextPage(view : View) {
            pageNumber += 1
            val offset = pageNumber * 100
            val url = URL("https://api.coinranking.com/v2/coins?limit=100&offset=${offset}")
            urlToCoins(url) {
                itemsAdapter = CustomAdapter(this, it as ArrayList<CoinX>)
                listView.adapter = itemsAdapter
            }
        }
        fun URL.downloadUrl() : String {
                val connection = this.openConnection() as HttpURLConnection
                val inputStream = connection.inputStream
                var result = ""
                inputStream.use {
                    result = IOUtils.toString(it, StandardCharsets.UTF_8)
                }
                return result
        }
    }