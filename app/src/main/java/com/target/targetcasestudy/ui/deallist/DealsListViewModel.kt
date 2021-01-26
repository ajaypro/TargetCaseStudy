package com.target.targetcasestudy.ui.deallist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.Deals
import com.target.targetcasestudy.data.DealsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

enum class DealListStatus { LOADING, ERROR, DONE }

class DealsListViewModel (private val dealsRepository: DealsRepository) : ViewModel() {

    private var _dealList = MutableLiveData<Deals>()
    val dealList: LiveData<Deals>
    get() = _dealList

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<DealListStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<DealListStatus>
        get() = _status

    init {
        viewModelScope.launch {
            _status.value = DealListStatus.LOADING
            try {
                _dealList.value =  dealsRepository.getDeals()
                dealsRepository.getDeals1()
                _status.value = DealListStatus.DONE
            } catch (e: Exception) {
                _status.value = DealListStatus.ERROR
                _dealList.value = Deals(emptyList())
            }

        }
    }

    /**
     * Variable that tells the Fragment to navigate to a specific [DealItemFragment]
     *
     * This is private because we don't want to expose setting this value to the Fragment.
     */
    private val _navigateToDealItem = MutableLiveData<DealItem>()

    /**
     * If this is non-null, immediately navigate to [DealItemFragment] and call [doneNavigating]
     */
    val navigateToDealItem: LiveData<DealItem>
        get() = _navigateToDealItem

    fun displayToDealItem(dealItem: DealItem) {
        _navigateToDealItem.value = dealItem
    }
    /**
     * Call this immediately after navigating to [DealItemFragment]
     *
     * It will clear the navigation request, so if the user rotates their phone it won't navigate
     * twice.
     */
    fun doneNavigating() {
        _navigateToDealItem.value = null
    }
}