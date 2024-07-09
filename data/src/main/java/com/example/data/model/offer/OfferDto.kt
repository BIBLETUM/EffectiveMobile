package com.example.data.model.offer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OfferDto(

    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("town")
    @Expose
    var town: String?,
    @SerializedName("price")
    @Expose
    var price: PriceDto?,

    )