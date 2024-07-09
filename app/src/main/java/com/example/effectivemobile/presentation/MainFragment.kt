package com.example.effectivemobile.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.databinding.FragmentMainBinding
import com.example.effectivemobile.presentation.utils.CyrillicInputFilter
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private lateinit var viewModel: MainFragmentViewModel

    //private lateinit var adapter: PokemonListAdapter

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]

//        val modalBottomSheet = BottomSheetSearch()
//        modalBottomSheet.show(requireActivity().supportFragmentManager, BottomSheetSearch.TAG)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    private fun setCyrillicFilter(){
        binding.arrivalET.filters = arrayOf(CyrillicInputFilter())
        binding.departureET.filters = arrayOf(CyrillicInputFilter())
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}