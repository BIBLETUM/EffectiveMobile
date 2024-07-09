package com.example.data.model.offers_tickets

import com.example.data.model.PriceDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TicketsOfferDto (
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("time_range")
    @Expose
    val timeRange: List<String>?,
    @SerializedName("price")
    @Expose
    val price: PriceDto?,
)