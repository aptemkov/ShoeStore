package com.github.aptemkov.shoestore.authorization

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.aptemkov.shoestore.R
import com.github.aptemkov.shoestore.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private lateinit var binding: FragmentAuthorizationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorizationBinding.bind(view)


        binding.signInButton.setOnClickListener { toSplashScreen() }
        binding.signUpButton.setOnClickListener { toSplashScreen() }
    }

    private fun toSplashScreen(): Boolean {
        var result = true

        if (!emailIsValid(binding.emailEditText.text.toString())) {
            binding.emailTextInput.error = "Email isn't valid"
            result = false
        }

        if (!passwordIsValid(binding.passwordEditText.text.toString())) {
            binding.passwordTextInput.error = "Password isn't valid"
            result = false
        }

        return when(result) {
            true -> {
                val action =
                    AuthorizationFragmentDirections.actionAuthorizationFragmentToWelcomeFragment(
                        binding.emailEditText.text.toString()
                    )
                findNavController().navigate(action)
                true
            }
            false -> false
        }


    }

    private fun emailIsValid(email: String): Boolean {
        return email.isNotBlank()
    }

    private fun passwordIsValid(password: String): Boolean {
        return password.isNotBlank()
    }
}