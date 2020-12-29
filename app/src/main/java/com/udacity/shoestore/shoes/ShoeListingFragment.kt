package com.udacity.shoestore.shoes

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.google.android.material.internal.ContextUtils
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShoeListingFragmentBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListingFragment : Fragment() {

    private lateinit var binding: ShoeListingFragmentBinding

    val viewModel: ShoeViewModel by activityViewModels { ShoeViewModelFactory() }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_listing_fragment, container, false)

        binding.shoeViewModel = viewModel

        binding.setLifecycleOwner(this)

        viewModel.listTemp.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach {
                addView(it, container)

                //val inflater:LayoutInflater = requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                binding.shoeItemLayout!!.addView(addView(it, container), binding.shoeItemLayout!!.childCount -1)
            }
        })

        binding.moreButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    fun addView(shoe: Shoe, container: ViewGroup?): View {

        val layout = LayoutInflater.from(context)

        val bindingItem: ShoeItemBinding = DataBindingUtil.inflate(layout, R.layout.shoe_item, container,false)

        with (bindingItem) {
            this.nameItemText.text = shoe.name

            this.companyItemText.text = shoe.company

            this.descriptionItemText.text = shoe.description

            this.sizeItemText.text = shoe.size.toString()
        }

        return bindingItem.root
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