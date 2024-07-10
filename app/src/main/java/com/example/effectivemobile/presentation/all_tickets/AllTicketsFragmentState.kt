package com.example.effectivemobile.presentation.all_tickets

import com.example.domain.model.ticket.TicketModel

sealed class AllTicketsFragmentState {
    data class Error(val errorMessage: String) : AllTicketsFragmentState()
    data object Loading : AllTicketsFragmentState()
    data class Content(
        val offers: List<TicketModel>
    ) : AllTicketsFragmentState()
}