package com.androidlearning.listviewtest

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class FruitAdapter(activity: Activity, val resourceId: Int, data: List<Fruit>) :
    ArrayAdapter<Fruit>(activity, resourceId, data) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)

        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)

        val fruit = getItem(position)

        fruit?.let {
            fruitImage.setImageResource(it.imageId)
            fruitName.text = it.name
        }

        return view
    }



}