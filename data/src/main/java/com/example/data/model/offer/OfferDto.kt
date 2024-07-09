package com.example.data.model.offer

import com.example.data.model.PriceDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OfferDto(

    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("town")
    @Expose
    val town: String?,
    @SerializedName("price")
    @Expose
    val price: PriceDto?,

    )