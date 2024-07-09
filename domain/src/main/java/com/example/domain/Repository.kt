package com.example.domain

import com.example.domain.model.offer.OfferModel
import com.example.domain.model.offer_ticket.TicketsOfferModel
import com.example.domain.model.ticket.TicketModel

interface Repository {

    suspend fun getOffers(): List<OfferModel>

    suspend fun getOfferTickets(): List<TicketsOfferModel>

    suspend fun getTickets(): List<TicketModel>

}