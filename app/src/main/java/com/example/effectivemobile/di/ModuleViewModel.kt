package com.example.effectivemobile.di

import androidx.lifecycle.ViewModel
import com.example.effectivemobile.presentation.bottom_sheet.BottomSheetSearchViewModel
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

}