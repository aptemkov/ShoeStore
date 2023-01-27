package com.github.aptemkov.shoestore.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.blue
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.aptemkov.shoestore.R
import com.github.aptemkov.shoestore.StoreViewModel
import com.github.aptemkov.shoestore.StoreViewModelFactory
import com.github.aptemkov.shoestore.databinding.FragmentShoeListBinding
import com.github.aptemkov.shoestore.models.Shoe
import kotlin.math.absoluteValue

class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StoreViewModel by activityViewModels {
        StoreViewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            // viewModel.addShoe(Shoe(name = "1Test", size = 12.3, company = "Apple", description = "tk erjfekw flh"))
            val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(-1)
            findNavController().navigate(action)
        }

        viewModel.list.observe(this.viewLifecycleOwner) {
            binding.layout.removeAllViews()
            it.forEach {
                addShoeToList(it)
            }
        }
    }

    private fun addShoeToList(shoe:Shoe) {
        val view = layoutInflater.inflate(R.layout.shoe_item, null)
        val nameView = view.findViewById<TextView>(R.id.shoe_name)
        val companyView = view.findViewById<TextView>(R.id.shoe_company)
        val sizeView = view.findViewById<TextView>(R.id.shoe_size)

        view.tag = shoe

        nameView.text = shoe.name
        companyView.text = shoe.company
        sizeView.text = shoe.size.toString()

        view.setOnClickListener{
            val id = viewModel.getId(shoe)
            val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(id)
            findNavController().navigate(action)
            //Toast.makeText(activity?.applicationContext, "$tag", Toast.LENGTH_SHORT).show()
        }

        binding.layout.addView(view)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}