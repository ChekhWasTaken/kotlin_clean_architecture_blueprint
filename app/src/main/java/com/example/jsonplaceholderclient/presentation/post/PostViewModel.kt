package com.example.jsonplaceholderclient.presentation.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.entity.Post
import com.example.domain.GetPostUseCase
import com.example.framework.BaseViewModel
import com.example.framework.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PostViewModel @Inject constructor(private val getPostUseCase: GetPostUseCase) :
    BaseViewModel() {
    private val _postLiveData = MutableLiveData<UIState<Post>>()
    val postLiveData = _postLiveData

    fun getPost(id: Int) {
        _postLiveData.value = UIState.Loading()
        viewModelScope.launch {
            try {
                _postLiveData.value = UIState.Success(getPostUseCase.execute(id))
            } catch (ex: java.lang.Exception) {
                _postLiveData.value = UIState.Error(ex)
            }
        }
    }

}