package com.udacity.shoestore.shoes

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel: ViewModel() {

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
    get() = _shoe

    val list:ArrayList<Shoe> = ArrayList()

    fun addShoeToList(name: String, size: String, company: String, description: String){
        val shoeTemp = Shoe(name, size.toDouble(), company, description)
        list.add(shoeTemp)
    }

}