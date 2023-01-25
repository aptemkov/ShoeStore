package com.github.aptemkov.shoestore.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.aptemkov.shoestore.R

class ShoeDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ShoeDetailsFragment()
    }

    private lateinit var viewModel: ShoeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_shoe_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShoeDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}