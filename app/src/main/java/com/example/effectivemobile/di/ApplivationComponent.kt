package com.example.effectivemobile.di

import android.app.Application
import com.example.effectivemobile.presentation.AllTicketsFragment
import com.example.effectivemobile.presentation.BottomSheetSearch
import com.example.effectivemobile.presentation.MainFragment
import com.example.effectivemobile.presentation.SearchFeaturedTicketsFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ModuleData::class, ModuleViewModel::class])
interface ApplicationComponent {

    fun inject(fragment: AllTicketsFragment)

    fun inject(fragment: BottomSheetSearch)

    fun inject(fragment: MainFragment)

    fun inject(fragment: SearchFeaturedTicketsFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent

    }
}