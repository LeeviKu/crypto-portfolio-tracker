package com.example.cryptotracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.android.material.textfield.TextInputEditText
import org.apache.commons.io.IOUtils
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
        lateinit var listView: RecyclerView
        lateinit var itemsAdapter: CustomAdapter
        lateinit var pageNumberView: TextView
        lateinit var previousButton: Button
        lateinit var searchTextInput: TextInputEditText
        var pageNumber = 0
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            listView = findViewById(R.id.listView)
            pageNumberView = findViewById(R.id.textView3)
            previousButton = findViewById(R.id.button)
            searchTextInput = findViewById(R.id.searchTextInput)
            searchTextInput.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    searchCoins(s.toString())
                }

            })
            previousButton.isEnabled = false
            val url = URL("https://api.coinranking.com/v2/coins?limit=100")
            urlToCoins(url) {
                itemsAdapter = CustomAdapter(it, this)
                listView.adapter = itemsAdapter
                (listView.adapter as CustomAdapter).notifyDataSetChanged()
            }

        }

        fun urlToCoins(url : URL, callback: (coins: List<CoinX>?) -> Unit) {
            thread {
                Log.d("alol", url.toString())
                val result = url.downloadUrl()
                Log.d("asd", result)
                if (result != "") {
                    val mp = jacksonObjectMapper()
                    val coin = mp.readValue<Coin>(result)
                    runOnUiThread() {
                        callback(coin.data?.coins)
                    }
                }
            }
        }

        fun nextPage(view : View) {
            previousButton.isEnabled = true
            pageNumber += 1
            val offset = pageNumber * 100
            val url = URL("https://api.coinranking.com/v2/coins?limit=100&offset=${offset}")
            pageNumberView.text = "page ${pageNumber + 1}"
            urlToCoins(url) {
                itemsAdapter = CustomAdapter(it, this)
                listView.adapter = itemsAdapter
                (listView.adapter as CustomAdapter).notifyDataSetChanged()
            }
        }

        fun searchCoins(searchWord: String) {
            var url = URL("https://api.coinranking.com/v2/search-suggestions?query=${searchWord}")
            urlToCoins(url) {
                val uuids: ArrayList<String> = ArrayList()
                var urlString: String = "https://api.coinranking.com/v2/coins?"
                it?.forEach {
                    uuids.add(it.uuid.toString())
                }
                uuids.forEach {
                    urlString += "uuids[]=$it&"
                }
                Log.d("lol", urlString)
                urlToCoins(URL(urlString)) {
                    itemsAdapter = CustomAdapter(it, this)
                    listView.adapter = itemsAdapter
                    (listView.adapter as CustomAdapter).notifyDataSetChanged()
                }

            }
        }

        fun previousPage(view : View) {
            pageNumber -= 1
            if (pageNumber < 1) {
                previousButton.isEnabled = false
            }
            val offset = pageNumber * 100
            val url = URL("https://api.coinranking.com/v2/coins?limit=100&offset=${offset}")
            pageNumberView.text = "page ${pageNumber + 1}"
            urlToCoins(url) {
                itemsAdapter = CustomAdapter(it, this)
                listView.adapter = itemsAdapter
                (listView.adapter as CustomAdapter).notifyDataSetChanged()
            }
        }
        fun URL.downloadUrl() : String {
            try {
                val connection = this.openConnection() as HttpURLConnection
                val inputStream = connection.inputStream
                var result = ""
                inputStream.use {
                    result = IOUtils.toString(it, StandardCharsets.UTF_8)
                }
                return result
            } catch (e: Exception) {
                Log.d("errro", "no file found")
            }
            return ""
        }
    }