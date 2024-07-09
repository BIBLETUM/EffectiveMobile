package com.example.effectivemobile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobile.R

class SearchFeaturedTicketsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_featured_tickets, container, false)
    }

    companion object {
        fun newInstance() =
            SearchFeaturedTicketsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}