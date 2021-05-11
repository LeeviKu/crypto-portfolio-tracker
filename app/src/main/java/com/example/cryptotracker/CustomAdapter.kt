package com.example.cryptotracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import java.util.*

class CustomAdapter(val context: Context, val arrayList: ArrayList<CoinX>) : BaseAdapter() {
    lateinit var img: ImageView
    lateinit var name: TextView
    lateinit var price: TextView

    constructor() {

    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        img = convertView.findViewById(R.id.icon)
        name = convertView.findViewById(R.id.listText)
        price = convertView.findViewById(R.id.listText2)
        name.text = arrayList[position].name
        val price2digits:Double = String.format("%.2f", arrayList[position].price?.toDouble()).toDouble()
        price.text = "${price2digits.toString()}$"
        img.loadSvgOrOthers(arrayList[position].iconUrl)
        return convertView
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    fun ImageView.loadSvgOrOthers(myUrl: String?) {
        myUrl?.let {
            if (it.toLowerCase(Locale.ENGLISH).endsWith("svg")) {
                val imageLoader = ImageLoader.Builder(this.context)
                        .componentRegistry {
                            add(SvgDecoder(this@loadSvgOrOthers.context))
                        }
                        .build()
                val request = LoadRequest.Builder(this.context)
                        .data(it)
                        .target(this)
                        .build()
                imageLoader.execute(request)
            } else {
                this.load(myUrl)
            }
        }
    }

}

