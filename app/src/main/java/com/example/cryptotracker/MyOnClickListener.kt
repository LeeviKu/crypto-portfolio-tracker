package com.example.cryptotracker

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MyOnClickListener(val viewHolder: CustomAdapter.ViewHolder, val activity: AppCompatActivity, val dataSet: List<CoinX>?) : View.OnClickListener {

    override fun onClick(v: View?) {
        if (activity.localClassName == "MainActivity") {
            val intent = Intent(activity, CoinActivity::class.java)
            Log.d("data", viewHolder.pos.toString())
            intent.putExtra("name", dataSet?.get(viewHolder.pos)?.name)
            intent.putExtra("marketCap", dataSet?.get(viewHolder.pos)?.marketCap)
            intent.putExtra("volume", dataSet?.get(viewHolder.pos)?.`24hVolume`)
            intent.putExtra("rank", dataSet?.get(viewHolder.pos)?.rank)
            intent.putExtra("price", dataSet?.get(viewHolder.pos)?.price)
            intent.putExtra("change", dataSet?.get(viewHolder.pos)?.change)
            activity.startActivity(intent)
        }

        if (activity.localClassName == "ChooseHoldingToChange") {
            val intent = Intent(activity, AddHoldingChange::class.java)
            intent.putExtra("name", dataSet?.get(viewHolder.pos)?.name)
            activity.startActivity(intent)
        }
    }
}