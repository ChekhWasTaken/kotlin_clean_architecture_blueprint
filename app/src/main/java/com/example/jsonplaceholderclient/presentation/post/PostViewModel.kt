package com.example.jsonplaceholderclient.presentation.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.entity.Post
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PostViewModel @Inject constructor(private val getPostUseCase: com.example.domain.GetPostUseCase) :
    ViewModel() {
    private val _postLiveData = MutableLiveData<UIState>()
    val postLiveData = _postLiveData

    fun getPost(id: Int) {
        _postLiveData.value = UIState.Loading
        viewModelScope.launch {
            try {
                _postLiveData.value = UIState.Success(getPostUseCase.execute(id))
            } catch (ex: java.lang.Exception) {
                _postLiveData.value = UIState.Error(ex)
            }
        }
    }

}

internal sealed class UIState {
    object Loading : UIState()
    class Error(val exception: Exception) : UIState()
    class Success(val data: Post) : UIState()
}