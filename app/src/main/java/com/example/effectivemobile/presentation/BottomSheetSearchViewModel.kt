package com.example.effectivemobile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BottomSheetSearchViewModel @Inject constructor() : ViewModel() {

    private val _shouldOpenFeaturedTicketsFragment = MutableStateFlow<Boolean>(false)
    val shouldOpenFeaturedTicketsFragment = _shouldOpenFeaturedTicketsFragment.asStateFlow()

    fun textChanged() {
        viewModelScope.launch {
            delay(4000)
            _shouldOpenFeaturedTicketsFragment.emit(true)
        }
    }

}
