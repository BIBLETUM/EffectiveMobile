package com.example.data.model.tickets

import com.example.data.model.PriceDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LuggageDto(

    @SerializedName("has_luggage")
    @Expose
    val hasLuggage: Boolean?,

    @SerializedName("price")
    @Expose
    val price: PriceDto?
)
