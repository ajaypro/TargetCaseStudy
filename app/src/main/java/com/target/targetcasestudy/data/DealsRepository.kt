package com.target.targetcasestudy.data

import com.target.targetcasestudy.data.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DealsRepository @Inject constructor(private val networkService: NetworkService) {

    suspend fun getDeals(): Deals = withContext(Dispatchers.Default) {
        networkService.doDealsCall()
    }

}