package com.example.data

import com.example.domain.Repository
import com.example.domain.model.offer.OfferModel
import com.example.domain.model.offer_ticket.TicketsOfferModel
import com.example.domain.model.ticket.TicketModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: Mapper,
) : Repository {

    override suspend fun getOffers(): List<OfferModel> {
        val offerResponse = apiService.getOffers()
        val offers = offerResponse.offers.map { mapper.mapOfferDtoToOfferModel(it) }
        return offers
    }

    override suspend fun getOfferTickets(): List<TicketsOfferModel> {
        val ticketsOfferResponse = apiService.getOfferTickets()
        val offerTickets =
            ticketsOfferResponse.ticketsOffers.map { mapper.mapOfferTicketDtoToTicketsOfferModel(it) }
        return offerTickets
    }

    override suspend fun getTickets(): List<TicketModel> {
        val ticketsResponse = apiService.getTickets()
        val tickets =
            ticketsResponse.tickets.map { mapper.mapTicketDtoToTicketModel(it) }
        return tickets
    }

}