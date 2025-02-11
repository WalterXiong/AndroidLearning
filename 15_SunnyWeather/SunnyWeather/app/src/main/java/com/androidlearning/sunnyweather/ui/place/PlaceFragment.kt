package com.androidlearning.sunnyweather.ui.place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.androidlearning.sunnyweather.R

class PlaceFragment : Fragment() {

    lateinit var adapter: PlaceAdapter

    companion object {
        fun newInstance() = PlaceFragment()
    }

    val viewModel: PlaceViewModel by lazy {
        ViewModelProvider(this)[PlaceViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }
}