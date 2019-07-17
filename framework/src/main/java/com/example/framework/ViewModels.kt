package com.example.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.IOUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel()

abstract class IOViewModel<Request, Response>(private val ioUseCase: IOUseCase<Request, Response>) : BaseViewModel() {
    private val _liveData = MutableLiveData<UIState<Response>>()

    fun operate(request: Request): LiveData<UIState<Response>> {
        viewModelScope.launch {
            _liveData.value = UIState.Loading()

            delay(5000)

            try {
                _liveData.value = UIState.Success(ioUseCase.execute(request))
            } catch (t: Throwable) {
                _liveData.value = UIState.Error(t)
            }
        }

        return _liveData
    }

}

fun <Response> IOViewModel<Unit, Response>.operate(): LiveData<UIState<Response>> {
    return operate(Unit)
}
