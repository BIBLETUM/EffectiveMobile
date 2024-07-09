package com.example.effectivemobile.presentation.offer

import com.example.domain.model.offer.OfferModel

sealed class OfferFragmentState {
    data class Error(val errorMessage: String) : OfferFragmentState()
    data object Loading : OfferFragmentState()
    data class Content(
        val offers: List<OfferModel>
    ) : OfferFragmentState()
}