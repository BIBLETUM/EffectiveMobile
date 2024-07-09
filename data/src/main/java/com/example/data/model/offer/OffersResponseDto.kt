package com.example.data.model.offer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OffersResponseDto(

    @SerializedName("offers")
    @Expose
    var offers: ArrayList<OfferDto>,

    )