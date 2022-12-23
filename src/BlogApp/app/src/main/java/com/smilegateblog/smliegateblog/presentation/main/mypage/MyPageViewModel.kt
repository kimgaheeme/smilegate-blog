package com.smilegateblog.smliegateblog.presentation.main.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.smilegateblog.smliegateblog.data.dto.post.GetMostViewedResponseItem
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.usecase.LoginUseCase
import com.smilegateblog.smliegateblog.domain.usecase.PostUseCase
import com.smilegateblog.smliegateblog.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val postUseCase: PostUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MyFragmentState>(MyFragmentState.Init)
    val state: StateFlow<MyFragmentState> get() = _state

    private val _myInfo = MutableStateFlow(User())
    val myInfo : StateFlow<User> get() = _myInfo

    val myPost = runBlocking { postUseCase.getMyPostUseCase().cachedIn(viewModelScope) }

    init {
        getMyInfo()
    }

    private fun setLoading(){
        _state.value = MyFragmentState.IsLoading(true)
    }

    private fun hideLoading(){
        _state.value = MyFragmentState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = MyFragmentState.ShowToast(message)
    }

    fun getMyInfo() {
        Log.d("GetMyInfo", "function exec")
        viewModelScope.launch {
            loginUseCase.getMyInfoUseCase()
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
                            _myInfo.value = result.data!!
                            Log.d("GetMyInfo", _myInfo.value.toString())
                        }
                        else -> {
                            showToast(result.message.toString())
                        }
                    }
                }
        }

    }
}

sealed class MyFragmentState {
    object Init : MyFragmentState()
    data class IsLoading(val isLoading: Boolean) : MyFragmentState()
    data class ShowToast(val message: String) : MyFragmentState()
}