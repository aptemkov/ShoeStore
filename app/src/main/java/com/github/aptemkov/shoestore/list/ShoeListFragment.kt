package com.github.aptemkov.shoestore.list

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.blue
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
            val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(
                /*id =*/-1,
                /*title =*/resources.getString(R.string.adding_title)
            )
            findNavController().navigate(action)
        }

        viewModel.list.observe(this.viewLifecycleOwner) {
            binding.layout.removeAllViews()
            it.forEach {
                addShoeToList(it)
            }
        }
    }

    private fun addShoeToList(shoe: Shoe) {
        val view = layoutInflater.inflate(R.layout.shoe_item, null)
        val nameView = view.findViewById<TextView>(R.id.shoe_name)
        val companyView = view.findViewById<TextView>(R.id.shoe_company)
        val sizeView = view.findViewById<TextView>(R.id.shoe_size)

        view.tag = shoe

        nameView.text = shoe.name
        companyView.text = shoe.company
        sizeView.text = shoe.size.toString()

        view.setOnClickListener {
            val id = viewModel.getId(shoe)
            val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(
                /*id =*/ id,
                /*title =*/ resources.getString(R.string.details_title))
            findNavController().navigate(action)
        }

        binding.layout.addView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                requireView().findNavController().navigateUp()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}