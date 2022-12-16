package com.smilegateblog.smliegateblog.presentation.postdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.smilegateblog.smliegateblog.data.dto.comment.GetCommentsResponseItem
import com.smilegateblog.smliegateblog.data.dto.post.GetPostResponse
import com.smilegateblog.smliegateblog.domain.usecase.CommentUseCase
import com.smilegateblog.smliegateblog.domain.usecase.PostUseCase
import com.smilegateblog.smliegateblog.domain.usecase.ScrapUseCase
import com.smilegateblog.smliegateblog.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val postUseCase: PostUseCase,
    private val scrapUseCase: ScrapUseCase,
    private val commentUseCase: CommentUseCase
) : ViewModel() {

    private var postId = 0

    private val _postDetail = MutableStateFlow(GetPostResponse())
    val postDetail : StateFlow<GetPostResponse> get() = _postDetail
    
    private val _state = MutableStateFlow<PostDetailFragmentState>(PostDetailFragmentState.Init)
    val state: StateFlow<PostDetailFragmentState> get() = _state

    var comment= commentUseCase.getCommentsUseCase(handle.get<Int>("postId")?: 0).cachedIn(viewModelScope)

    init {
        postId = handle.get<Int>("postId")?: 0
        getPostDetail()
    }

    private fun setLoading(){
        _state.value = PostDetailFragmentState.IsLoading(true)
    }

    private fun hideLoading(){
        _state.value = PostDetailFragmentState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = PostDetailFragmentState.ShowToast(message)
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

sealed class PostDetailFragmentState {
    object Init : PostDetailFragmentState()
    data class IsLoading(val isLoading: Boolean) : PostDetailFragmentState()
    data class ShowToast(val message: String) : PostDetailFragmentState()
}
