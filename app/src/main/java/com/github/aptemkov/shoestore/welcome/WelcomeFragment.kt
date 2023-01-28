package com.github.aptemkov.shoestore.welcome

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.aptemkov.shoestore.R
import com.github.aptemkov.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private val navigationArgs: WelcomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)
        renderAnimations()
        binding.nextBtn.setOnClickListener { toWelcomeFragment() }
    }

    private fun renderAnimations() {
        binding.loadingIndicator.alpha = 0f
        binding.loadingIndicator.animate()
            .alpha(0.7f)
            .setDuration(3000)
            .start()

        binding.pleaseWaitTextView.alpha = 0f
        binding.pleaseWaitTextView.animate()
            .alpha(1f)
            .setStartDelay(500)
            .setDuration(3000)
            .start()
        binding.nextBtn.alpha = 0f
        binding.nextBtn.animate()
            .alpha(1f)
            .setStartDelay(500)
            .setDuration(3000)
            .start()

    }

    private fun toWelcomeFragment() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment(navigationArgs.username)
        findNavController().navigate(action)
    }
}