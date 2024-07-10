package com.example.effectivemobile.presentation.featured_tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeaturedTicketsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow<FeaturedTicketsState>(FeaturedTicketsState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _state.value = FeaturedTicketsState.Content(offers = repository.getOfferTickets())
            } catch (e: Exception) {
                _state.value = FeaturedTicketsState.Error(e.toString())
            }
        }
    }

}