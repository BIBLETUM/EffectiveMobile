package com.example.effectivemobile.di

import androidx.lifecycle.ViewModel
import com.example.effectivemobile.presentation.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ModuleViewModel {

    @IntoMap
    @Binds
    @ViewModelKey(MainFragmentViewModel::class)
    fun bindMainFragmentViewModel(impl: MainFragmentViewModel): ViewModel
//
//    @IntoMap
//    @ViewModelKey(PokemonProfileViewModel::class)
//    @Binds
//    fun bindPokemonProfileViewModel(impl: PokemonProfileViewModel): ViewModel

}