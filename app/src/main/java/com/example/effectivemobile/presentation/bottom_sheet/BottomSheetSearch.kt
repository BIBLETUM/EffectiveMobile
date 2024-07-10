package com.example.effectivemobile.presentation.bottom_sheet

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentBottomSheetSearchBinding
import com.example.effectivemobile.presentation.EffectiveMobileApplication
import com.example.effectivemobile.presentation.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class BottomSheetSearch : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetSearchBinding? = null
    private val binding: FragmentBottomSheetSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentBottomSheetSearchBinding == null")

    private lateinit var viewModel: BottomSheetSearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as EffectiveMobileApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private var departureText = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog as BottomSheetDialog
        val bottomSheet =
            dialog.findViewById<View>(R.id.standard_bottom_sheet) as LinearLayout
        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPressed()

        arguments?.let {
            departureText = it.getString(DEPARTURE_KEY) ?: ""
        }
        binding.departureET.text = departureText

        viewModel =
            ViewModelProvider(this, viewModelFactory)[BottomSheetSearchViewModel::class.java]
        setUpListeners()
        observeViewModel()
    }

    private fun onBackPressed() {
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                dismiss()
                true
            } else {
                false
            }
        }
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.shouldOpenFeaturedTicketsFragment.collect { shouldOpenFeaturedTicketsFragment ->
                if (shouldOpenFeaturedTicketsFragment) {
                    delay(500)
                    val resultBundle = Bundle().apply {
                        putString(ARRIVAL_KEY, binding.arrivalET.text.toString())
                    }
                    setFragmentResult(REQUEST_KEY, resultBundle)
                    dismiss()
                }
            }
        }
    }

    private fun setUpListeners() {
        with(binding) {
            anywhere.setOnClickListener {
                arrivalET.setText(getString(R.string.anywhere))
                viewModel.textChanged()
            }

            istanbulLL.setOnClickListener {
                arrivalET.setText(getString(R.string.istanbul))
                viewModel.textChanged()
            }

            sochiLL.setOnClickListener {
                arrivalET.setText(getString(R.string.sochi))
                viewModel.textChanged()
            }

            phuketLL.setOnClickListener {
                arrivalET.setText(getString(R.string.phuket))
                viewModel.textChanged()
            }

            complexRoute.setOnClickListener {
                openPlaceholder()
            }
            dayOffs.setOnClickListener {
                openPlaceholder()
            }
            lastMinuteTickets.setOnClickListener {
                openPlaceholder()
            }
        }
    }

    private fun openPlaceholder() {
        val resultBundle = Bundle().apply {
            putBoolean(SHOULD_OPEN_PLACEHOLDER, true)
        }
        setFragmentResult(REQUEST_KEY, resultBundle)
        dismiss()
    }

    companion object {
        const val REQUEST_KEY = "bottomSheetRequestKey"
        const val ARRIVAL_KEY = "ArrivalKey"
        const val DEPARTURE_KEY = "DepartureKey"
        const val SHOULD_OPEN_PLACEHOLDER = "OpenPlaceholder"
        fun newInstance(departureText: String): BottomSheetSearch {
            val fragment = BottomSheetSearch()
            val args = Bundle().apply {
                putString(DEPARTURE_KEY, departureText)
            }
            fragment.arguments = args
            return fragment
        }
    }
}