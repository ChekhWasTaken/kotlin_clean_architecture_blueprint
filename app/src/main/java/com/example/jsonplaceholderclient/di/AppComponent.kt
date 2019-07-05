package com.example.jsonplaceholderclient.di

import com.example.jsonplaceholderclient.ClientApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        FragmentModule::class,
        ActivityModule::class,
        ApiModule::class,
        DataModule::class,
        DomainModule::class,
        DbModule::class
    ]
)
interface AppComponent {
    fun inject(clientApplication: ClientApplication)
}