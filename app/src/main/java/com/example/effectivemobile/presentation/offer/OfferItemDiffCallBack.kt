package com.example.effectivemobile.presentation.offer

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.offer.OfferModel

object OfferItemDiffCallBack : DiffUtil.ItemCallback<OfferModel>() {

    override fun areItemsTheSame(oldItem: OfferModel, newItem: OfferModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OfferModel, newItem: OfferModel): Boolean {
        return oldItem == newItem
    }

}