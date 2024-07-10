package com.example.effectivemobile.presentation.offer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentMainBinding
import com.example.effectivemobile.presentation.EffectiveMobileApplication
import com.example.effectivemobile.presentation.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private lateinit var viewModel: MainFragmentViewModel

    private lateinit var adapter: OfferItemAdapter

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
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        getTextsFromPrefs()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]
        adapter = OfferItemAdapter()
        binding.offersRV.adapter = adapter
        setUpListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is OfferFragmentState.Error -> {
                        binding.progress.isVisible = false
                        Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_SHORT)
                            .show()
                    }

                    is OfferFragmentState.Loading -> {
                        binding.progress.isVisible = false
                    }

                    is OfferFragmentState.Content -> {
                        binding.progress.isVisible = false
                        adapter.submitList(state.offers)
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.shouldOpenModalWindow.collect { shouldOpenModalWindow ->
                if (shouldOpenModalWindow) {
                    val action =
                        MainFragmentDirections.actionMainFragmentToBottomSheetSearch(binding.departureET.text.toString())
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.fill_departure_field), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun getTextsFromPrefs() {
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)
        val departureText = sharedPreferences?.getString(DEPARTURE_KEY, "") ?: ""
        val arrivalText = sharedPreferences?.getString(ARRIVAL_KEY, "") ?: ""
        binding.departureET.setText(departureText)
        binding.arrivalET.setText(arrivalText)
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPreferences.edit()) {
            putString(DEPARTURE_KEY, binding.departureET.text.toString())
            putString(ARRIVAL_KEY, binding.arrivalET.text.toString())
            apply()
        }
    }


    private fun setUpListeners() {
        with(binding) {
            arrivalET.setOnClickListener {
                viewModel.textChanged(departureET.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = MainFragment()
        const val DEPARTURE_KEY = "departureText"
        const val ARRIVAL_KEY = "arrivalText"
    }
}