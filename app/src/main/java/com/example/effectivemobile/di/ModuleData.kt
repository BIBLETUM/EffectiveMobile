package com.example.effectivemobile.di

import android.app.Application
import com.example.data.ApiFactory
import com.example.data.ApiService
import com.example.data.RepositoryImpl
import com.example.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ModuleData {

    @Binds
    @ApplicationScope
    fun bindRepository(impl: RepositoryImpl): Repository

    companion object {
        @Provides
        @ApplicationScope
        fun provideApiService(application: Application): ApiService {
            return ApiFactory.createApiService(application)
        }
    }

}