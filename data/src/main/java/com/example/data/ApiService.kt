package com.example.data

import com.example.data.model.offer.OffersResponseDto
import com.example.data.model.offers_tickets.OfferTicketsResponseDto
import com.example.data.model.tickets.TicketsResponseDto
import retrofit2.http.GET

interface ApiService {

    @GET("offers.json")
    suspend fun getOffers(): OffersResponseDto

    @GET("offers_tickets.json")
    suspend fun getOfferTickets(): OfferTicketsResponseDto

    @GET("tickets.json")
    suspend fun getTickets(): TicketsResponseDto

}