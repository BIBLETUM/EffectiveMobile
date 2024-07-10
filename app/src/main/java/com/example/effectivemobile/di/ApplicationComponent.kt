package com.example.effectivemobile.di

import android.app.Application
import com.example.effectivemobile.presentation.AllTicketsFragment
import com.example.effectivemobile.presentation.bottom_sheet.BottomSheetSearch
import com.example.effectivemobile.presentation.featured_tickets.SearchFeaturedTicketsFragment
import com.example.effectivemobile.presentation.offer.MainFragment
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