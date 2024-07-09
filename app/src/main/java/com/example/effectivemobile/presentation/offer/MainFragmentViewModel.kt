package com.example.effectivemobile.presentation.offer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow<OfferFragmentState>(OfferFragmentState.Loading)
    val state = _state.asStateFlow()

    private val _shouldOpenModalWindow = MutableStateFlow<Boolean>(false)
    val shouldOpenModalWindow = _shouldOpenModalWindow.asStateFlow()

    private var _textArrival: String = ""
    private var _textDeparture: String = ""

    fun textChanged(textArrival: String, textDeparture: String) {
        _textArrival = textArrival
        _textDeparture = textDeparture
        when (_textArrival.isNotEmpty() && _textDeparture.isNotEmpty()) {
            (true) -> {
                viewModelScope.launch {
                    _shouldOpenModalWindow.emit(true)
                }
            }
            false -> {
                viewModelScope.launch {
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