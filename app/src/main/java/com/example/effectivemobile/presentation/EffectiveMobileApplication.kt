package com.example.effectivemobile.presentation

import android.app.Application
import com.example.effectivemobile.di.DaggerApplicationComponent

class EffectiveMobileApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}