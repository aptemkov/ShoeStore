package com.github.aptemkov.shoestore.instruction

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.aptemkov.shoestore.R
import com.github.aptemkov.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment(R.layout.fragment_instruction) {

    private lateinit var binding: FragmentInstructionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInstructionBinding.bind(view)
        binding.startBtn.setOnClickListener {
            val action = InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment()
            findNavController().navigate(action)
        }
    }

}