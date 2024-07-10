package com.example.effectivemobile.presentation.offer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.offer.OfferModel
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.OfferItemBinding
import java.text.NumberFormat
import java.util.Locale

class OfferItemAdapter() :
    ListAdapter<OfferModel, OfferViewHolder>(OfferItemDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = OfferItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = getItem(position)
        val binding = holder.binding

        with(binding) {
            title.text = offer.title
            town.text = offer.town
            price.text = formatPrice(offer.price.toInt())
            val backGroundId = when (position) {
                0 -> R.drawable.offer_image_0
                1 -> R.drawable.offer_image_1
                2 -> R.drawable.offer_image_2
                else -> R.drawable.offer_image_0
            }
            image.setImageResource(backGroundId)
        }
    }

    private fun formatPrice(number: Int): String {
        val numberFormat = NumberFormat.getInstance(Locale("ru", "RU"))
        val formattedNumber = numberFormat.format(number)

        return "от $formattedNumber ₽"
    }
}