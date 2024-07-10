package com.example.effectivemobile.presentation.featured_tickets

import com.example.domain.model.offer_ticket.TicketsOfferModel

sealed class FeaturedTicketsState {
    data class Error(val errorMessage: String) : FeaturedTicketsState()
    data object Loading : FeaturedTicketsState()
    data class Content(
        val offers: List<TicketsOfferModel>
    ) : FeaturedTicketsState()
}