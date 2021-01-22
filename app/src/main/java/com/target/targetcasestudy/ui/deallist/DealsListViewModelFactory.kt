package com.target.targetcasestudy.ui.deallist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.data.DealsRepository
import com.target.targetcasestudy.data.network.NetworkService

class DealsListViewModelFactory(private val networkService: NetworkService) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DealsListViewModel::class.java)) {
                return DealsListViewModel(DealsRepository(networkService)) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }

    }
