package com.udacity.shoestore.shoes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding


class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<ShoeDetailFragmentBinding>(inflater, R.layout.shoe_detail_fragment, container, false)

        val parentLinearLayout = view?.findViewById<LinearLayout>(R.id.shoe_item_layout)

        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        binding.shoeViewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.cancelButton.setOnClickListener { view: View ->

            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())
        }

        binding.saveButton.setOnClickListener { view: View ->
            val name = binding.shoeNameText.text.toString()
            val size = binding.shoeSizeText.text.toString()
            val company = binding.companyText.text.toString()
            val description = binding.descriptionText.text.toString()
            if(name == "" || size == "" || company == "" || description == "") {
                Toast.makeText(context, "There are some fields empty", Toast.LENGTH_SHORT).show()
            }else {
                viewModel.addShoeToList(name, size, company, description)
                view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
                view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}