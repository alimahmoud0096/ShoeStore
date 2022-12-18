package com.udacity.shoestore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {

    var shoe = Shoe()
    private val shoes = MutableLiveData<MutableList<Shoe>>()

    init {
        //initialize list with empty list
        shoes.value = ArrayList()
    }


    fun getShoesItems(): LiveData<MutableList<Shoe>> {
        return shoes
    }

    fun addNewItem() {
        shoes.value!!.add(shoe)
    }

}