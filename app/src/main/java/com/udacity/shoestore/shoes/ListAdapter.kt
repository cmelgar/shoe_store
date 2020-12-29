package com.udacity.shoestore.shoes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe

class ListAdapter(context: Context, shoeList: ArrayList<Shoe>):BaseAdapter() {

    private val aContext: Context = context
    private val aShoeList: ArrayList<Shoe> = shoeList
    override fun getCount(): Int {
        return aShoeList.count()
    }

    override fun getItem(p0: Int): Any {
        return aShoeList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return  p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val layout = LayoutInflater.from(aContext)

        val binding:ShoeItemBinding = DataBindingUtil.inflate(layout, R.layout.shoe_item, p2,false)

        val shoe:Shoe = getItem(p0) as Shoe

        binding.nameItemText.text = shoe.name

        binding.companyItemText.text = shoe.company

        binding.descriptionItemText.text = shoe.description

        binding.sizeItemText.text = shoe.size.toString()

        return binding.root
    }


}