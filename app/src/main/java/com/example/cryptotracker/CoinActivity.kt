package com.example.cryptotracker

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.net.URL
import java.text.NumberFormat
import java.util.*

class CoinActivity() : AppCompatActivity() {
    lateinit var nameView: TextView
    lateinit var marketCapView: TextView
    lateinit var volumeView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coin)
        nameView = findViewById(R.id.coinName)
        marketCapView = findViewById(R.id.marketCap)
        volumeView = findViewById(R.id.volume)
        nameView.text = intent.getStringExtra("name")
        var marketCap = String.format("%.0f", intent.getStringExtra("marketCap")?.toDouble())
        var volume = String.format("%.0f", intent.getStringExtra("volume")?.toDouble())
        marketCap = NumberFormat.getNumberInstance(Locale.US).format(marketCap.toBigInteger())
        volume = NumberFormat.getNumberInstance(Locale.US).format(volume.toBigInteger())
        marketCapView.text = "Market cap: ${marketCap}$"
        volumeView.text = "Volume 24h: ${volume}$"
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}