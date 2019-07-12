package com.example.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.IOUseCase
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel()

open class IOViewModel<Request, Response>(private val IOUseCase: IOUseCase<Request, Response>) : BaseViewModel() {
    private val _liveData = MutableLiveData<UIState<Response>>()

    fun operate(request: Request): LiveData<UIState<Response>> {
        viewModelScope.launch {
            _liveData.value = UIState.Loading()

            try {
                _liveData.value = UIState.Success(IOUseCase.execute(request))
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
