package com.udacity.shoestore.instructions

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionsFragmentBinding
import com.udacity.shoestore.models.Shoe


class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<InstructionsFragmentBinding>(inflater, R.layout.instructions_fragment, container, false)

        binding.goStoreButton.setOnClickListener { view: View ->
            view.findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeListingFragment())
        }

        return binding.root
    }
}