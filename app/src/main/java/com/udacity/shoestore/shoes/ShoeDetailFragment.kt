package com.udacity.shoestore.shoes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe


class ShoeDetailFragment : Fragment() {

    val viewModel: ShoeViewModel by activityViewModels { ShoeViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<ShoeDetailFragmentBinding>(inflater, R.layout.shoe_detail_fragment, container, false)

        binding.shoeViewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.cancelButton.setOnClickListener { view: View ->

            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment( ))
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

        return binding.root
    }
}
