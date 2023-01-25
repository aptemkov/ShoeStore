package com.github.aptemkov.shoestore.instruction

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.aptemkov.shoestore.R

class InstructionFragment : Fragment() {

    companion object {
        fun newInstance() = InstructionFragment()
    }

    private lateinit var viewModel: InstructionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_instruction, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InstructionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}