package com.example.effectivemobile.presentation.featured_tickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.offer_ticket.TicketsOfferModel
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FlightPreviewItemBinding
import java.text.NumberFormat
import java.util.Locale

class OfferTicketItemAdapter() :
    ListAdapter<TicketsOfferModel, OfferTicketViewHolder>(OfferTickerItemDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferTicketViewHolder {
        val binding = FlightPreviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OfferTicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferTicketViewHolder, position: Int) {
        val offer = getItem(position)
        val binding = holder.binding

        with(binding) {
            companyName.text = offer.title
            availableTimeTV.text = offer.timeRange?.joinToString(" ")
            priceTV.text = formatPrice(offer.price?.toInt() ?: 0)
            val backGroundId = when (position) {
                0 -> R.drawable.circle_red
                1 -> R.drawable.circle_blue
                2 -> R.drawable.circle_white
                else -> R.drawable.circle_blue
            }
            itemCircle.setBackgroundResource(backGroundId)
        }
    }

    private fun formatPrice(number: Int): String {
        val numberFormat = NumberFormat.getInstance(Locale("ru", "RU"))
        val formattedNumber = numberFormat.format(number)

        return "$formattedNumber ₽"
    }
}