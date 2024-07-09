package com.example.effectivemobile.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentBottomSheetSearchBinding
import com.example.effectivemobile.presentation.offer.MainFragmentViewModel
import com.example.effectivemobile.presentation.offer.OfferItemAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collect
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
        val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[BottomSheetSearchViewModel::class.java]
        setUpListeners()
        observeViewModel()
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            viewModel.shouldOpenFeaturedTicketsFragment.collect{ shouldOpenFeaturedTicketsFragment ->
                if (shouldOpenFeaturedTicketsFragment) {
                    Log.d("Aboba", "dasdasd")
                }
            }
        }
    }

    private fun setUpListeners() {
        with(binding) {
            anywhere.setOnClickListener{
                arrivalET.setText(getString(R.string.anywhere))
                viewModel.textChanged()
            }

            istanbulLL.setOnClickListener{
                arrivalET.setText(getString(R.string.istanbul))
                viewModel.textChanged()
            }

            sochiLL.setOnClickListener{
                arrivalET.setText(getString(R.string.sochi))
                viewModel.textChanged()
            }

            phuketLL.setOnClickListener{
                arrivalET.setText(getString(R.string.phuket))
                viewModel.textChanged()
            }
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

}