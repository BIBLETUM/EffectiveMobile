package com.example.data

import com.example.data.model.offer.OffersResponseDto
import retrofit2.http.GET

interface ApiService {

    @GET("offers.json")
    suspend fun getOffers(): OffersResponseDto

//    @GET("categories.php")
//    suspend fun getCategoriesList(): CategoriesListDto

}