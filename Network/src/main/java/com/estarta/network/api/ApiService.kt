package com.estarta.network.api

import com.estarta.network.models.GetItemsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(ApiConstants.GET_ITEMS_LIST)
    fun getItemsList(): Single<GetItemsResponse>
}