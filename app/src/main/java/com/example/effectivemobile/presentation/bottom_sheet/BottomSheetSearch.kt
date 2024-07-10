package com.example.effectivemobile.presentation.bottom_sheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
        val args: BottomSheetSearchArgs by navArgs()
        departureText = args.departureText
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog as BottomSheetDialog
        val bottomSheet =
            dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[BottomSheetSearchViewModel::class.java]
        binding.departureET.text = departureText
        setUpListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.shouldOpenFeaturedTicketsFragment.collect { shouldOpenFeaturedTicketsFragment ->
                if (shouldOpenFeaturedTicketsFragment) {
                    delay(500)
                    val action =
                        BottomSheetSearchDirections.actionBottomSheetSearchToSearchFeaturedTicketsFragment(
                            departureText,
                            binding.arrivalET.text.toString()
                        )
                    findNavController().navigate(action)
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
        val action =
            BottomSheetSearchDirections.actionBottomSheetSearchToPlaceholderFragment2()
        findNavController().navigate(action)
    }
}