package com.example.listviewtest

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class FruitAdapter(activity: Activity, val resourceId: Int, data: List<Fruit>) :
    ArrayAdapter<Fruit>(activity, resourceId, data) {


    inner class ViewHolder(var fruitImage: ImageView, var fruitName: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {

            view = LayoutInflater.from(context).inflate(resourceId, parent, false)

            val fruitImage: ImageView = view.findViewById(R.id.fruit_image)
            val fruitName: TextView = view.findViewById(R.id.fruit_name)

            viewHolder = ViewHolder(fruitImage, fruitName)
            view.tag = viewHolder

        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }


        val fruit: Fruit? = getItem(position)
        fruit?.let {
            viewHolder.fruitImage.setImageResource(it.imageId)
            viewHolder.fruitName.text = it.name
        }
//        fruit?.let {
//            fruitImage.setImageResource(it.imageId)
//            fruitName.text = it.name
//        }
        return view
    }
}