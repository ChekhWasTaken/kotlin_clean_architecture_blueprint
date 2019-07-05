package com.example.jsonplaceholderclient.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.entity.Post
import com.example.domain.GetPostsForUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class UserViewModel @Inject constructor(private val getPostsForUserUseCase: GetPostsForUserUseCase) :
    ViewModel() {
    private val _userLiveData = MutableLiveData<UIState>()
    val userLiveData: LiveData<UIState> = _userLiveData

    fun getPostsForUser(userId: Int) {
        _userLiveData.value = UIState.Loading
        viewModelScope.launch {
            try {
                _userLiveData.value = UIState.Success(getPostsForUserUseCase.execute(userId))
            } catch (ex: Exception) {
                _userLiveData.value = UIState.Error(ex)
            }
        }
    }
}

internal sealed class UIState {
    object Loading : UIState()
    class Error(val exception: Exception) : UIState()
    class Success(val data: List<Post>) : UIState()
}