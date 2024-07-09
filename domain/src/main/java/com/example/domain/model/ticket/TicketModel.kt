package com.example.domain.model.ticket

data class TicketModel(
    val id: Int,
    val badge: String?,
    val price: String?,
    val providerName: String,
    val company: String,
    val departure: DepartureModel?,
    val arrival: ArrivalModel?,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: LuggageModel?,
    val handLuggage: HandLuggageModel?,
    val isReturnable: Boolean,
    val isExchangeable: Boolean,
)
