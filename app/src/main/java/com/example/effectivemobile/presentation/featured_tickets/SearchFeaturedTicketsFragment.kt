package com.example.effectivemobile.presentation.featured_tickets

import android.app.DatePickerDialog
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
import androidx.navigation.fragment.navArgs
import com.example.effectivemobile.databinding.FragmentSearchFeaturedTicketsBinding
import com.example.effectivemobile.presentation.EffectiveMobileApplication
import com.example.effectivemobile.presentation.ViewModelFactory
import com.example.effectivemobile.presentation.getDayOfWeekByDayOffWeek
import com.example.effectivemobile.presentation.getMonthStringByInt
import com.example.effectivemobile.presentation.parseCalendar
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Calendar
import javax.inject.Inject

class SearchFeaturedTicketsFragment : Fragment() {

    private var _binding: FragmentSearchFeaturedTicketsBinding? = null
    private val binding: FragmentSearchFeaturedTicketsBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchFeaturedTicketsBinding == null")

    private lateinit var viewModel: FeaturedTicketsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var adapter: OfferTicketItemAdapter

    private var departureText = ""
    private var arrivalText = ""

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
        _binding = FragmentSearchFeaturedTicketsBinding.inflate(inflater, container, false)
        val args: SearchFeaturedTicketsFragmentArgs by navArgs()
        departureText = args.departureText
        arrivalText = args.arrivalText
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[FeaturedTicketsViewModel::class.java]
        binding.departureET.text = departureText
        binding.arrivalET.text = arrivalText

        adapter = OfferTicketItemAdapter()
        binding.flightsRV.adapter = adapter

        setChipDate(Calendar.getInstance())
        setUpListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is FeaturedTicketsState.Error -> {
                        binding.progress.isVisible = false
                        Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_SHORT)
                            .show()
                    }

                    is FeaturedTicketsState.Loading -> {
                        binding.progress.isVisible = true
                    }

                    is FeaturedTicketsState.Content -> {
                        binding.progress.isVisible = false
                        adapter.submitList(state.offers)
                    }
                }
            }
        }
    }

    private fun setUpListeners() {
        with(binding) {
            swapIcon.setOnClickListener {
                swapDepartureAndArrivalTexts()
            }
            backTicketChip.setOnClickListener {
                showDatePickerDialog(false)
            }
            dateChip.setOnClickListener {
                showDatePickerDialog(true)
            }
        }
    }

    private fun setChipDate(calendar: Calendar) {
        val date = parseCalendar(calendar)
        binding.dayMonthTV.text = String.format("%s %s, ", date.day, date.month)
        binding.dayOffWeekTV.text = String.format("%s", date.dayOfWeek)
    }

    private fun showDatePickerDialog(setToText: Boolean) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                when (setToText) {
                    true -> {
                        val selectedDate = LocalDate.of(selectedYear, selectedMonth, selectedDay)
                        val selectedDayOfWeek: DayOfWeek = selectedDate.dayOfWeek
                        val date = Date(
                            day = selectedDay.toString(),
                            month = getMonthStringByInt(selectedMonth),
                            dayOfWeek = getDayOfWeekByDayOffWeek(selectedDayOfWeek)
                        )
                        binding.dayMonthTV.text = String.format("%s %s, ", date.day, date.month)
                        binding.dayOffWeekTV.text = String.format("%s", date.dayOfWeek)
                    }

                    false -> {}
                }
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun swapDepartureAndArrivalTexts() {
        val temp = binding.arrivalET.text
        binding.arrivalET.text = binding.departureET.text
        binding.departureET.text = temp
    }

}