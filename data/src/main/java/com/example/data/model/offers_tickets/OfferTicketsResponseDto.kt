package com.example.data.model.offers_tickets

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OfferTicketsResponseDto(
    @SerializedName("tickets_offers")
    @Expose
    val ticketsOffers: ArrayList<TicketsOfferDto>,
)