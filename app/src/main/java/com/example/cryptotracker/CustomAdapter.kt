package com.example.cryptotracker

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import org.w3c.dom.Text
import java.util.*

class CustomAdapter(private val dataSet: List<CoinX>?, val activity: AppCompatActivity) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.listText)
        val img: ImageView = view.findViewById(R.id.icon)
        val price: TextView = view.findViewById(R.id.listText2)
        val change: TextView = view.findViewById(R.id.listText3)
        var pos = -1

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)
        val vHolder = ViewHolder(view)
        val myOnClickListener: View.OnClickListener = MyOnClickListener(vHolder, activity, dataSet)
        view.setOnClickListener(myOnClickListener)
        return vHolder
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Log.d("test", dataSet?.get(position)?.name.toString())
        viewHolder.name.text = dataSet?.get(position)?.name
        val twoDigitPrice = String.format("%.3f", dataSet?.get(position)?.price?.toDouble())
        viewHolder.price.text = "${twoDigitPrice}$"
        viewHolder.img.loadSvgOrOthers(dataSet?.get(position)?.iconUrl)
        viewHolder.pos = position
        val twoDigitChange = "${String.format("%.2f", dataSet?.get(position)?.change?.toDouble())}%"
        viewHolder.change.text = twoDigitChange
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet!!.size

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


