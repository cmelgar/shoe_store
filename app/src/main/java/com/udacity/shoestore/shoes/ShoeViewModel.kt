package com.udacity.shoestore.shoes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel(): ViewModel() {

    private val _shoe = MutableLiveData<Shoe>()

    val shoe: LiveData<Shoe>
    get() = _shoe

    var listTemp2:ArrayList<Shoe> = arrayListOf()
    private val list = MutableLiveData<ArrayList<Shoe>>()
    val listTemp : LiveData<ArrayList<Shoe>>
        get() = list

    fun addShoeToList(name: String, size: String, company: String, description: String){
        val shoeTemp = Shoe(name, size.toDouble(), company, description)
        listTemp2.add(shoeTemp)
        list.value = listTemp2
    }

    fun getShoe()  {
        listTemp2.forEach {
            _shoe.value = it
        }
    }
}