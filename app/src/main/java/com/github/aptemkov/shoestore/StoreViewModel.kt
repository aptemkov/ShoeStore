package com.github.aptemkov.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.aptemkov.shoestore.models.Shoe

class StoreViewModel : ViewModel() {
    private val _list = MutableLiveData<MutableList<Shoe>>()
    val list: LiveData<MutableList<Shoe>> = _list

    fun addShoe(shoe: Shoe) {
        val tempList = mutableListOf<Shoe>()
        _list.value?.let { tempList.addAll(it) }
        tempList.add(shoe)
        _list.value = tempList
    }

    fun getId(shoe: Shoe): Int {
        var a = _list.value

        _list.value?.let {
            return it.indexOfFirst { it == shoe }
        }

        return 0
    }

    fun getById(position: Int): Shoe {
        list.value?.let {
            if(position <= it.size) {
                return it[position]
            }
        }
        return Shoe("", 0.0, "", "")
    }
}

class StoreViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StoreViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}