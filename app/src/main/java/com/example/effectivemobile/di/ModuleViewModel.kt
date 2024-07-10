package com.example.effectivemobile.di

import androidx.lifecycle.ViewModel
import com.example.effectivemobile.presentation.all_tickets.AllTicketsViewModel
import com.example.effectivemobile.presentation.bottom_sheet.BottomSheetSearchViewModel
import com.example.effectivemobile.presentation.featured_tickets.FeaturedTicketsViewModel
import com.example.effectivemobile.presentation.offer.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ModuleViewModel {

    @IntoMap
    @Binds
    @ViewModelKey(MainFragmentViewModel::class)
    fun bindMainFragmentViewModel(impl: MainFragmentViewModel): ViewModel

    @IntoMap
    @ViewModelKey(BottomSheetSearchViewModel::class)
    @Binds
    fun bindBottomSheetSearchViewModel(impl: BottomSheetSearchViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FeaturedTicketsViewModel::class)
    @Binds
    fun bindFeaturedTicketsViewModel(impl: FeaturedTicketsViewModel): ViewModel

    @IntoMap
    @ViewModelKey(AllTicketsViewModel::class)
    @Binds
    fun bindAllTicketsViewModelViewModel(impl: AllTicketsViewModel): ViewModel

}