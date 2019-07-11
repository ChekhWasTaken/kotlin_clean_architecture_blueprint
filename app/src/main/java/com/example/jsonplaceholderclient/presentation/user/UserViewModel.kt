package com.example.jsonplaceholderclient.presentation.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.entity.Post
import com.example.domain.GetPostsForUserUseCase
import com.example.framework.BaseViewModel
import com.example.framework.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class UserViewModel @Inject constructor(private val getPostsForUserUseCase: GetPostsForUserUseCase) :
    BaseViewModel() {

    private val _userLiveData = MutableLiveData<UIState<List<Post>>>()
    val userLiveData = _userLiveData

    fun getPostsForUser(userId: Int) {
        _userLiveData.value = UIState.Loading()
        viewModelScope.launch {
            try {
                _userLiveData.value = UIState.Success(getPostsForUserUseCase.execute(userId))
            } catch (ex: Exception) {
                _userLiveData.value = UIState.Error(ex)
            }
        }
    }
}