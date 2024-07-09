package com.example.effectivemobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effectivemobile.databinding.FragmentBottomSheetSearchBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetSearch : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetSearchBinding? = null
    private val binding: FragmentBottomSheetSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentBottomSheetSearchBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

}