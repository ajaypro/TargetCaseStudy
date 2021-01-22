package com.target.targetcasestudy.data.network

import com.target.targetcasestudy.data.Deals
import com.target.targetcasestudy.data.network.Endpoints.DEALS
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(DEALS)
    suspend fun doDealsCall(): Deals

}