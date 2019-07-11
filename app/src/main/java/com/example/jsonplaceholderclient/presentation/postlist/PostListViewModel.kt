package com.example.jsonplaceholderclient.presentation.postlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.entity.Post
import com.example.domain.GetPostsUseCase
import com.example.domain.execute
import com.example.framework.BaseViewModel
import com.example.framework.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PostListViewModel @Inject constructor(private val getPostsUseCase: GetPostsUseCase) :
    BaseViewModel() {
    private val _postsLiveData = MutableLiveData<UIState<List<Post>>>()
    val postsLiveData = _postsLiveData

    fun getPosts() {
        _postsLiveData.value = UIState.Loading()
        viewModelScope.launch {
            try {
                _postsLiveData.value = UIState.Success(getPostsUseCase.execute())
            } catch (ex: Exception) {
                _postsLiveData.value = UIState.Error(ex)
            }
        }
    }
}