package com.example.data.model.tickets

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TicketsResponseDto(

    @SerializedName("tickets")
    @Expose
    val tickets: ArrayList<TicketsDto>,

)