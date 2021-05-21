package com.example.cryptotracker

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

/**
 * Activity class for choosing crpyto you want to add to your portfolio
 */
class ChooseHoldingToChange : AppCompatActivity() {
    lateinit var listView: RecyclerView
    lateinit var itemsAdapter: CryptoListAdapter
    lateinit var searchTextInput: TextInputEditText
    lateinit var data: List<CoinX>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_holding_to_change)
        listView = findViewById(R.id.listView)
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
        val url = URL("https://api.coinranking.com/v2/coins?limit=100")
        urlToCoins(url) {
            if (it !== null) {
                data = it
            }
            itemsAdapter = CryptoListAdapter(it, this)
            listView.adapter = itemsAdapter
            (listView.adapter as CryptoListAdapter).notifyDataSetChanged()
        }

    }

    /**
     * parses coinranking url can calls callback(data: List<CoinX>?)
     *
     * @param url url to parse
     * @param callback callback function that is called after parsing
     */
    fun urlToCoins(url : URL, callback: (coins: List<CoinX>?) -> Unit) {
        thread {
            val result = url.downloadUrl()
            if (result != "") {
                val mp = jacksonObjectMapper()
                val coin = mp.readValue<Coin>(result)
                runOnUiThread() {
                    callback(coin.data?.coins)
                }
            }
        }
    }

    /**
     * parses url with searchword and puts it into recycleView
     *
     * @param searchWord word you want to search with
     */
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
                itemsAdapter = CryptoListAdapter(it, this)
                listView.adapter = itemsAdapter
                (listView.adapter as CryptoListAdapter).notifyDataSetChanged()
            }

        }
    }

    /**
     * Creates HTTPURLConnection and downloads data form URL
     */
    fun URL.downloadUrl() : String {
        try {
            val connection = this.openConnection() as HttpURLConnection
            val inputStream = connection.inputStream
            var result: String
            inputStream.use {
                result = IOUtils.toString(it, StandardCharsets.UTF_8)
            }
            return result
        } catch (e: Exception) {
            Log.d("error", "no file found")
        }
        return ""
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, Portfolio::class.java)
        startActivity(intent)
    }

}