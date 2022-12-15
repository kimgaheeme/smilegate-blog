package com.smilegateblog.smliegateblog.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponse
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponseItem
import com.smilegateblog.smliegateblog.domain.usecase.PostUseCase
import com.smilegateblog.smliegateblog.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(postUseCase: PostUseCase) : ViewModel() {

    private val _state = MutableStateFlow<HomeFragmentState>(HomeFragmentState.Init)
    val state: StateFlow<HomeFragmentState> get() = _state

    val recentPost = postUseCase.getRecentPostUseCase().cachedIn(viewModelScope)

    private fun setLoading(){
        _state.value = HomeFragmentState.IsLoading(true)
    }

    private fun hideLoading(){
        _state.value = HomeFragmentState.IsLoading(false)
    }


}


sealed class HomeFragmentState {
    object Init : HomeFragmentState()
    data class IsLoading(val isLoading: Boolean) : HomeFragmentState()
}