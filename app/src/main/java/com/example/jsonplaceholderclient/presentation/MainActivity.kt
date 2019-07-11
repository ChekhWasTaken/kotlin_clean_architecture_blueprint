package com.example.jsonplaceholderclient.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.example.framework.BaseActivity
import com.example.jsonplaceholderclient.R
import dagger.android.AndroidInjection
import javax.inject.Inject

internal class MainActivity : BaseActivity() {
    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        supportFragmentManager.fragmentFactory = fragmentFactory

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
