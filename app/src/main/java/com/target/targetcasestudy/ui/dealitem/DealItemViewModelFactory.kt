package com.target.targetcasestudy.ui.dealitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.data.DealItem

class DealItemViewModelFactory(private val dealItem: DealItem) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DealItemViewModel::class.java)) {
            return DealItemViewModel(dealItem) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}

