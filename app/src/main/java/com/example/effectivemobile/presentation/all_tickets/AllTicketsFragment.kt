package com.example.effectivemobile.presentation.all_tickets

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
import androidx.navigation.fragment.navArgs
import com.example.effectivemobile.databinding.FragmentAllTicketsBinding
import com.example.effectivemobile.presentation.EffectiveMobileApplication
import com.example.effectivemobile.presentation.ViewModelFactory
import com.example.effectivemobile.presentation.parseDate
import com.example.effectivemobile.presentation.parsePassengers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

class AllTicketsFragment : Fragment() {

    private var _binding: FragmentAllTicketsBinding? = null
    private val binding: FragmentAllTicketsBinding
        get() = _binding ?: throw RuntimeException("FragmentAllTicketsBinding == null")

    private lateinit var viewModel: AllTicketsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as EffectiveMobileApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private lateinit var adapter: TicketItemAdapter

    private var departureText = ""
    private var arrivalText = ""
    private var passengers = ""
    private var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAllTicketsBinding.inflate(inflater, container, false)

        val args: AllTicketsFragmentArgs by navArgs()
        parseParams(args)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[AllTicketsViewModel::class.java]

        setInitialText()
        adapter = TicketItemAdapter()
        binding.flightsRV.adapter = adapter

        setUpListeners()
        observeViewModel()
    }

    private fun setInitialText() {
        with(binding) {
            routeTV.setText(String.format("%s - %s", departureText, arrivalText))
            datePassengersTV.setText(String.format("%s, %s", date, passengers))
        }
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is AllTicketsFragmentState.Error -> {
                        binding.progress.isVisible = false
                        Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_SHORT)
                            .show()
                    }

                    is AllTicketsFragmentState.Loading -> {
                        binding.progress.isVisible = true
                    }

                    is AllTicketsFragmentState.Content -> {
                        binding.progress.isVisible = false
                        adapter.submitList(state.offers)
                    }
                }
            }
        }
    }

    private fun setUpListeners() {
        with(binding) {
            backIV.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun parseParams(args: AllTicketsFragmentArgs) {
        departureText = args.departure
        arrivalText = args.arrival
        passengers = parsePassengers(args.passenger)
        date = parseDate(args.date)
    }
}