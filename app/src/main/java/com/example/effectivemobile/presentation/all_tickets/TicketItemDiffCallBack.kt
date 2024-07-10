package com.example.effectivemobile.presentation.all_tickets

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.ticket.TicketModel

class TicketItemDiffCallBack: DiffUtil.ItemCallback<TicketModel>() {

    override fun areItemsTheSame(oldItem: TicketModel, newItem: TicketModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TicketModel, newItem: TicketModel): Boolean {
        return oldItem == newItem
    }
}