package com.androidlearning.recyclerviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FruitAdapterStaggeredGrid(val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapterStaggeredGrid.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage_staggered)
        val fruitName: TextView = view.findViewById(R.id.fruitName_staggered)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fruit_item_staggered, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList.get(position)
            Toast.makeText(
                parent.context, "你点击了 view ${fruit.name}",
                Toast.LENGTH_SHORT
            ).show()
        }


        viewHolder.fruitImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList.get(position)
            Toast.makeText(
                parent.context, "你点击了 image ${fruit.name}",
                Toast.LENGTH_SHORT
            ).show()
        }


        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList.get(position)
        holder.fruitName.text = fruit.name
        holder.fruitImage.setImageResource(fruit.imageId)
    }

    override fun getItemCount() = fruitList.size

}