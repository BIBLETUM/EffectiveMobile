package com.example.domain.model.offer_ticket

data class TicketsOfferModel(
    val id: Int,
    val title: String?,
    val timeRange: List<String>?,
    val price: String?,
)
