package com.example.effectivemobile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.effectivemobile.databinding.FragmentSearchFeaturedTicketsBinding

class SearchFeaturedTicketsFragment : Fragment() {

    private var _binding: FragmentSearchFeaturedTicketsBinding? = null
    private val binding: FragmentSearchFeaturedTicketsBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchFeaturedTicketsBinding == null")

    private var departureText = ""
    private var arrivalText = ""

    private val component by lazy {
        (requireActivity().application as EffectiveMobileApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
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
//        viewModel =
//            ViewModelProvider(this, viewModelFactory)[BottomSheetSearchViewModel::class.java]
        binding.departureET.text = departureText
        binding.arrivalET.text = arrivalText
//        setUpListeners()
//        observeViewModel()
    }

}