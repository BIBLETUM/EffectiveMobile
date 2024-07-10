package com.example.effectivemobile.presentation.all_tickets

import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.databinding.FlightItemBinding
import com.example.effectivemobile.databinding.FlightItemWithCommentBinding

class TicketWithoutBadgeViewHolder(val binding: FlightItemBinding) :
    RecyclerView.ViewHolder(binding.root)

class TicketWithBadgeViewHolder(val binding: FlightItemWithCommentBinding) :
    RecyclerView.ViewHolder(binding.root)