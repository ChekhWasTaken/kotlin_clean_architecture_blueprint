package com.example.jsonplaceholderclient.di

import com.example.jsonplaceholderclient.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface ActivityModule {
    @ContributesAndroidInjector
    fun mainActivity(): MainActivity
}