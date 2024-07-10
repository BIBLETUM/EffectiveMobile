package com.example.effectivemobile.presentation.offer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow<OfferFragmentState>(OfferFragmentState.Loading)
    val state = _state.asStateFlow()

    private val _shouldOpenModalWindow = MutableSharedFlow<Boolean>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val shouldOpenModalWindow = _shouldOpenModalWindow.asSharedFlow()

    private var _textArrival: String = ""

    fun textChanged(textArrival: String) {
        _textArrival = textArrival
        viewModelScope.launch {
            when (_textArrival.isNotEmpty()) {
                (true) -> {
                    _shouldOpenModalWindow.emit(true)
                }

                false -> {
                    _shouldOpenModalWindow.emit(false)
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            try {
                _state.value = OfferFragmentState.Content(offers = repository.getOffers())
            } catch (e: Exception) {
                _state.value = OfferFragmentState.Error(e.toString())
            }
        }
    }
}