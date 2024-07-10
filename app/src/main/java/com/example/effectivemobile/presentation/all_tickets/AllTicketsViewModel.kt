package com.example.effectivemobile.presentation.all_tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllTicketsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow<AllTicketsFragmentState>(AllTicketsFragmentState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _state.value = AllTicketsFragmentState.Content(offers = repository.getTickets())
            } catch (e: Exception) {
                _state.value = AllTicketsFragmentState.Error(e.toString())
            }
        }
    }
}