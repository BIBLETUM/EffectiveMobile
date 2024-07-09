package com.example.data

import com.example.data.model.offer.OfferDto
import com.example.data.model.offers_tickets.TicketsOfferDto
import com.example.data.model.tickets.TicketsDto
import com.example.domain.model.offer.OfferModel
import com.example.domain.model.offer_ticket.TicketsOfferModel
import com.example.domain.model.ticket.ArrivalModel
import com.example.domain.model.ticket.DepartureModel
import com.example.domain.model.ticket.HandLuggageModel
import com.example.domain.model.ticket.LuggageModel
import com.example.domain.model.ticket.TicketModel
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapOfferDtoToOfferModel(dto: OfferDto) = OfferModel(
        dto.id,
        dto.title,
        dto.town,
        dto.price?.value.toString()
    )

    fun mapOfferTicketDtoToTicketsOfferModel(dto: TicketsOfferDto) = TicketsOfferModel(
        id = dto.id,
        title = dto.title,
        timeRange = dto.timeRange,
        price = dto.price?.value.toString()
    )

    fun mapTicketDtoToTicketModel(dto: TicketsDto) = TicketModel(
        id = dto.id,
        badge = dto.badge,
        price = dto.price?.value.toString(),
        providerName = dto.providerName,
        company = dto.company,
        departure = DepartureModel(
            dto.departure?.town,
            dto.departure?.date,
            dto.departure?.airport
        ),
        arrival = ArrivalModel(
            dto.arrival?.town,
            dto.arrival?.date,
            dto.arrival?.airport
        ),
        hasTransfer = dto.hasTransfer,
        hasVisaTransfer = dto.hasVisaTransfer,
        luggage = LuggageModel(
            dto.luggage?.hasLuggage,
            dto.luggage?.price?.value.toString()
        ),
        handLuggage = HandLuggageModel(
            dto.handLuggage?.hasHandLuggage,
            dto.handLuggage?.size
        ),
        isReturnable = dto.isReturnable,
        isExchangeable = dto.isExchangeable
    )


}