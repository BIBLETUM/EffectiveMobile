package com.example.effectivemobile.presentation.featured_tickets

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.offer_ticket.TicketsOfferModel

object OfferTickerItemDiffCallBack : DiffUtil.ItemCallback<TicketsOfferModel>() {

    override fun areItemsTheSame(oldItem: TicketsOfferModel, newItem: TicketsOfferModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TicketsOfferModel,
        newItem: TicketsOfferModel
    ): Boolean {
        return oldItem == newItem
    }

}