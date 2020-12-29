package com.udacity.shoestore.shoes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListingFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeListingFragment : Fragment() {

    private lateinit var binding: ShoeListingFragmentBinding
    private lateinit var viewModel: ShoeViewModel
    private lateinit var viewModelFactory: ShoeViewModelFactory
    //val viewModel: ShoeViewModel by activityViewModels { ShoeViewModelFactory(viewModel.list) }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_listing_fragment, container, false)

        val shoeFragmentArgs by navArgs<ShoeListingFragmentArgs>()
        viewModelFactory = ShoeViewModelFactory(shoeFragmentArgs.shoeList as ArrayList<Shoe>)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeViewModel::class.java)
        //binding.shoeViewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.listShoe.adapter = ListAdapter(requireContext(), viewModel.list)


        binding.moreButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment(viewModel.list))
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
                requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}