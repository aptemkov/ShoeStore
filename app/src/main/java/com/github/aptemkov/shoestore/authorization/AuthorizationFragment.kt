package com.github.aptemkov.shoestore.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.aptemkov.shoestore.R
import com.github.aptemkov.shoestore.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private lateinit var viewModel: AuthorizationViewModel
    private lateinit var binding: FragmentAuthorizationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorizationBinding.bind(view)
        viewModel = ViewModelProvider(this).get(AuthorizationViewModel::class.java)


        binding.signInButton.setOnClickListener { toSplashScreen() }
        binding.signUpButton.setOnClickListener { toSplashScreen() }
    }

    private fun toSplashScreen() {
        val action = AuthorizationFragmentDirections.actionAuthorizationFragmentToWelcomeFragment()
        findNavController().navigate(action)
    }
}