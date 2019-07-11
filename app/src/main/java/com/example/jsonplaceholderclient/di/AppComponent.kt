package com.example.jsonplaceholderclient.di

import com.example.jsonplaceholderclient.ClientApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        DbModule::class,
        DataModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(clientApplication: ClientApplication)
}