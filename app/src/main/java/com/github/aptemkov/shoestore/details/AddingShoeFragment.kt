package com.github.aptemkov.shoestore.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.aptemkov.shoestore.StoreViewModel
import com.github.aptemkov.shoestore.StoreViewModelFactory
import com.github.aptemkov.shoestore.databinding.FragmentShoeDetailsBinding
import com.github.aptemkov.shoestore.models.Shoe
import com.github.aptemkov.shoestore.welcome.WelcomeFragmentArgs
import com.google.android.material.snackbar.Snackbar

class AddingShoeFragment : Fragment() {

    private var _binding: FragmentShoeDetailsBinding? = null
    private val binding get() = _binding!!
    private val navigationArgs: AddingShoeFragmentArgs by navArgs()

    private val viewModel: StoreViewModel by activityViewModels {
        StoreViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentShoeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        when(navigationArgs.id) {
            -1 -> {}
            else -> {
                val shoe = viewModel.getById(navigationArgs.id)
                binding.nameEditText.setText(shoe.name)
                binding.sizeEditText.setText(shoe.size.toString())
                binding.companyEditText.setText(shoe.company)
                binding.descriptionEditText.setText(shoe.description)

                binding.saveBtn.visibility = View.GONE
            }
        }


        binding.cancelBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.saveBtn.setOnClickListener {
            saveShoe()
        }
    }

    private fun saveShoe() {
        val name = binding.nameEditText.text.toString()
        val size = binding.sizeEditText.text.toString()
        val company = binding.companyEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()

        if (name.isNotBlank() && size.isNotBlank()
            && company.isNotBlank() && description.isNotBlank()
        ) {
            viewModel.addShoe(
                Shoe(
                    name = name,
                    size = size.toDouble(),
                    company = company,
                    description = description,
                )
            )
            findNavController().popBackStack()
        }
        else {
            Snackbar.make(binding.root, "All fields are necessary!", Snackbar.LENGTH_SHORT)
                .show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}