package com.smilegateblog.smliegateblog.presentation.editpost

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.smilegateblog.smliegateblog.data.dto.comment.GetCommentsResponseItem
import com.smilegateblog.smliegateblog.data.dto.comment.PostCommentRequest
import com.smilegateblog.smliegateblog.data.dto.comment.PutCommentRequest
import com.smilegateblog.smliegateblog.data.dto.post.GetPostResponse
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.usecase.CommentUseCase
import com.smilegateblog.smliegateblog.domain.usecase.PostUseCase
import com.smilegateblog.smliegateblog.domain.usecase.ScrapUseCase
import com.smilegateblog.smliegateblog.presentation.login.LoginActivityState
import com.smilegateblog.smliegateblog.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPostViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val postUseCase: PostUseCase
) : ViewModel() {

    var postId = handle.get<Int>("postId")?: 0
    var isUpdate = postId != 0

    private val _postDetail = MutableStateFlow(GetPostResponse())
    val postDetail : StateFlow<GetPostResponse> get() = _postDetail
    
    private val _state = MutableStateFlow<PostDetailActivityState>(PostDetailActivityState.Init)
    val state: StateFlow<PostDetailActivityState> get() = _state


    private fun setLoading(){
        _state.value = PostDetailActivityState.IsLoading(true)
    }
    

    private fun hideLoading(){
        _state.value = PostDetailActivityState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = PostDetailActivityState.ShowToast(message)
    }


    fun getPostDetail() {
        Log.d("Get Post Detail", "function exec")
        viewModelScope.launch {
            postUseCase.getPostUseCase(postId)
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
                            _postDetail.value = result.data!!
                            Log.d("Get Post Detail", _postDetail.value.toString())
                        }
                        else -> {
                            showToast(result.message.toString())
                        }
                    }
                }
        }
    }



}

sealed class PostDetailActivityState {
    object Init : PostDetailActivityState()
    data class IsLoading(val isLoading: Boolean) : PostDetailActivityState()
    data class ShowToast(val message: String) : PostDetailActivityState()
}
