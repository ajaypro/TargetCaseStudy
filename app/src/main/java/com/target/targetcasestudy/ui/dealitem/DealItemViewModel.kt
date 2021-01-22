package com.target.targetcasestudy.ui.dealitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.DealItem

class DealItemViewModel(dealItem: DealItem): ViewModel() {

    private val _selectedDealItem = MutableLiveData<DealItem>()
    val selectedDealItem: LiveData<DealItem> = _selectedDealItem

    /**
     * Update the selected DealItem livedata when viewmodel is initialized, this livedata is observed
     * by dealItemfragment to show the dealItem details.
     */
    init {
        _selectedDealItem.value = dealItem
    }

}