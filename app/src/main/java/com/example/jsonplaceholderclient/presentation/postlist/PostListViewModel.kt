package com.example.jsonplaceholderclient.presentation.postlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.entity.Post
import com.example.domain.execute
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PostListViewModel @Inject constructor(private val getPostsUseCase: com.example.domain.GetPostsUseCase) :
    ViewModel() {
    private val _postsLiveData = MutableLiveData<UIState>()
    val postsLiveData = _postsLiveData

    fun getPosts() {
        _postsLiveData.value = UIState.Loading
        viewModelScope.launch {
            try {
                _postsLiveData.value = UIState.Success(getPostsUseCase.execute())
            } catch (ex: Exception) {
                _postsLiveData.value = UIState.Error(ex)
            }
        }
    }
}

internal sealed class UIState {
    object Loading : UIState()
    class Error(val exception: Exception) : UIState()
    class Success(val data: List<Post>) : UIState()
}