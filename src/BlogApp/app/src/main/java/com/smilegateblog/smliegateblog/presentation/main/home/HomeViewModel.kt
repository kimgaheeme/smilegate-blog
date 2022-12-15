package com.smilegateblog.smliegateblog.presentation.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.GetMostViewedResponse
import com.smilegateblog.smliegateblog.data.dto.post.GetMostViewedResponseItem
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponse
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponseItem
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.usecase.PostUseCase
import com.smilegateblog.smliegateblog.domain.usecase.login.LoginUseCase
import com.smilegateblog.smliegateblog.presentation.login.LoginActivityState
import com.smilegateblog.smliegateblog.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val postUseCase: PostUseCase) : ViewModel() {

    private val _state = MutableStateFlow<HomeFragmentState>(HomeFragmentState.Init)
    val state: StateFlow<HomeFragmentState> get() = _state

    val recentPost = postUseCase.getRecentPostUseCase().cachedIn(viewModelScope)

    private val _mostViewedPost = MutableStateFlow<List<GetMostViewedResponseItem>>(mutableListOf())
    val mostViewedPost : StateFlow<List<GetMostViewedResponseItem>> get() = _mostViewedPost

    init {
        getMostViewedPost()
    }

    private fun setLoading(){
        _state.value = HomeFragmentState.IsLoading(true)
    }

    private fun hideLoading(){
        _state.value = HomeFragmentState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = HomeFragmentState.ShowToast(message)
    }

    fun getMostViewedPost() {
        Log.d("GetMostViewedPost", "function exec")
        viewModelScope.launch {
            postUseCase.getMostViewedPostUseCase()
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    when(result) {
                        is Resource.Success -> {
                            _mostViewedPost.value = result.data!!
                            Log.d("많이 조회한 게시물", _mostViewedPost.value.first().toString())
                        }
                        else -> {
                            showToast(result.message.toString())
                        }
                    }
                }
        }

    }

}


sealed class HomeFragmentState {
    object Init : HomeFragmentState()
    data class IsLoading(val isLoading: Boolean) : HomeFragmentState()
    data class ShowToast(val message: String) : HomeFragmentState()
}