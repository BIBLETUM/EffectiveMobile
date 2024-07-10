package com.example.effectivemobile.presentation.placeholders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobile.databinding.FragmentPlaceholderMenuBinding

class PlaceholderFragmentMenu : Fragment() {

    private var _binding: FragmentPlaceholderMenuBinding? = null
    private val binding: FragmentPlaceholderMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentPlaceholderMenuBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceholderMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}