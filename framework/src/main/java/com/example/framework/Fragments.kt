package com.example.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.framework.databinding.FragmentIoBinding
import kotlin.reflect.KClass

open class BaseFragment : Fragment()

abstract class IoFragment<Request, Response>(viewModelFactory: ViewModelProvider.Factory) :
    BaseFragment() {

    private val ioViewModel: IOViewModel<Request, Response> by createViewModelLazy(
        viewModelClass,
        { viewModelStore },
        { viewModelFactory }
    )

    abstract val viewModelClass: KClass<out IOViewModel<Request, Response>>

    abstract val request: Request

    private lateinit var binding: FragmentIoBinding

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIoBinding.inflate(inflater, container, false)

        binding.content.addView(onCreateContentView(inflater, container, savedInstanceState))

        return binding.root
    }

    abstract fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ioViewModel.operate(request).observe(this, Observer {
            when (it) {
                is UIState.Loading -> {
                    binding.isLoading = true
                    onLoading()
                }
                is UIState.Error -> {
                    binding.isLoading = false
                    onError(it.ex)
                }
                is UIState.Success -> {
                    binding.isLoading = false
                    onSuccess(it.data)
                }
            }
        })
    }

    open fun onLoading() {}

    abstract fun onError(throwable: Throwable)

    abstract fun onSuccess(data: Response)
}

abstract class ResponseIoFragment<Response>(viewModelFactory: ViewModelProvider.Factory) :
    IoFragment<Unit, Response>(viewModelFactory) {

    override val request: Unit
        get() = Unit

}