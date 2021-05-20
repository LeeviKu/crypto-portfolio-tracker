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
import java.util.*

class PortfolioAdapter(private val dataSet: List<Holding>?, val activity: AppCompatActivity) : RecyclerView.Adapter<PortfolioAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.holdingName)
        var amount: TextView = view.findViewById(R.id.holdingAmount)
        var price: TextView = view.findViewById(R.id.holdingPrice)


        init {}
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.holding, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Log.d("asd", dataSet?.get(position).toString())
        if (dataSet != null) {
            holder.name.text = dataSet[position].name
            holder.amount.text = dataSet[position].amount
            holder.price.text = dataSet[position].price
        }
    }
        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet!!.size

}
