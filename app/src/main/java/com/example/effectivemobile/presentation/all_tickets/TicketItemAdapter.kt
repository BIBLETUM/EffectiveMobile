package com.example.effectivemobile.presentation.all_tickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ticket.TicketModel
import com.example.effectivemobile.databinding.FlightItemBinding
import com.example.effectivemobile.databinding.FlightItemWithCommentBinding
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class TicketItemAdapter() :
    ListAdapter<TicketModel, RecyclerView.ViewHolder>(TicketItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_WITH_BADGE -> {
                val binding = FlightItemWithCommentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TicketWithBadgeViewHolder(binding)
            }

            VIEW_TYPE_WITHOUT_BADGE -> {
                val binding =
                    FlightItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                TicketWithoutBadgeViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder.itemViewType) {
            VIEW_TYPE_WITH_BADGE -> {
                holder as TicketWithBadgeViewHolder
                with(holder.binding) {
                    commentTV.text = item.badge
                    priceTV.text = item.price
                    timeStartTV.text =
                        item.departure?.date?.substringAfterLast('T')?.substring(0, 5)
                    timeEndTV.text = item.arrival?.date?.substringAfterLast('T')?.substring(0, 5)
                    originTV.text = item.departure?.airport
                    destinationTV.text = item.arrival?.airport
                    conditionsTV.text = makeConditionsText(item)
                }
            }

            VIEW_TYPE_WITHOUT_BADGE -> {
                holder as TicketWithoutBadgeViewHolder
                with(holder.binding) {
                    priceTV.text = item.price
                    timeStartTV.text =
                        item.departure?.date?.substringAfterLast('T')?.substring(0, 5)
                    timeEndTV.text = item.arrival?.date?.substringAfterLast('T')?.substring(0, 5)
                    originTV.text = item.departure?.airport
                    destinationTV.text = item.arrival?.airport
                    conditionsTV.text = makeConditionsText(item)
                }
            }
        }
    }

    private fun countFlightLength(ticketModel: TicketModel): String {
        val dateTimeString1 = ticketModel.departure?.date ?: ""
        val dateTimeString2 = ticketModel.arrival?.date ?: ""

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

        val dateTime1 = LocalDateTime.parse(dateTimeString1, formatter)
        val dateTime2 = LocalDateTime.parse(dateTimeString2, formatter)

        val duration = Duration.between(dateTime1, dateTime2)

        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60
        return String.format("%s.%sч в пути", hours, minutes)
    }

    private fun makeConditionsText(ticketModel: TicketModel): String {
        val duration = countFlightLength(ticketModel)
        return if (ticketModel.hasTransfer) {
            duration
        } else
            String.format("%s / без пересадок", duration)
    }


    override fun getItemViewType(position: Int): Int {
        val ticket = getItem(position)
        return when (!ticket.badge.isNullOrEmpty()) {
            true -> VIEW_TYPE_WITH_BADGE
            false -> VIEW_TYPE_WITHOUT_BADGE
        }
    }

    companion object {
        const val VIEW_TYPE_WITH_BADGE = 1
        const val VIEW_TYPE_WITHOUT_BADGE = 0
    }

}
