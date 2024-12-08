package com.androidlearning.recyclerviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FruitAdapterHorizon(val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapterHorizon.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 横向布局
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage_horizon)
        val fruitName: TextView = view.findViewById(R.id.fruitName_horizon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fruit_item_horizon, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList.get(position)
        holder.fruitName.text = fruit.name
        holder.fruitImage.setImageResource(fruit.imageId)
    }

    override fun getItemCount() = fruitList.size

}