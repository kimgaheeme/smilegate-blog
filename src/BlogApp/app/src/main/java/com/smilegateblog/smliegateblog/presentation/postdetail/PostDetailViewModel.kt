package com.smilegateblog.smliegateblog.presentation.postdetail

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
class PostDetailViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val postUseCase: PostUseCase,
    private val scrapUseCase: ScrapUseCase,
    private val commentUseCase: CommentUseCase
) : ViewModel() {

    private var postId = 0

    private val _postDetail = MutableStateFlow(GetPostResponse())
    val postDetail : StateFlow<GetPostResponse> get() = _postDetail
    
    private val _state = MutableStateFlow<PostDetailActivityState>(PostDetailActivityState.Init)
    val state: StateFlow<PostDetailActivityState> get() = _state

    var comment= commentUseCase.getCommentsUseCase(handle.get<Int>("postId")?: 0).cachedIn(viewModelScope)

    init {
        postId = handle.get<Int>("postId")?: 0
        getPostDetail()
    }

    private fun setLoading(){
        _state.value = PostDetailActivityState.IsLoading(true)
    }
    
    private fun successAddComment(commentId: Int) {
        _state.value = PostDetailActivityState.SuccessAddComment(commentId)
    }

    fun setIsUpdateComment(commentId: Int){
        _state.value = PostDetailActivityState.IsUpdateComment(true, commentId)
    }

    private fun successUpdateComment(){
        _state.value = PostDetailActivityState.IsUpdateComment(false, 0)
    }

    private fun hideLoading(){
        _state.value = PostDetailActivityState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = PostDetailActivityState.ShowToast(message)
    }

    fun deletePost() {
        viewModelScope.launch {
            postUseCase.delPostUseCase(postId).collect{ result ->
                hideLoading()
                when(result) {
                    is Resource.Success -> {
                        Log.d("delete post", "delete post successful")
                    }
                    else -> {
                        showToast(result.message.toString())
                    }
                }
            }
        }
    }

    fun deleteComment(commentId: Int) {
        viewModelScope.launch {
            commentUseCase.delCommentUseCase(commentId).collect(){ result ->
                hideLoading()
                when(result) {
                    is Resource.Success -> {
                        Log.d("delete comment", "delete comment successful")
                    }
                    else -> {
                        showToast(result.message.toString())
                    }
                }
            }
        }
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

    fun sendComment(comment: String) {
        when(state.value){
            is PostDetailActivityState.IsUpdateComment -> updateComment(PutCommentRequest(comment), (state.value as PostDetailActivityState.IsUpdateComment).commentId!!)
            else -> addComment(PostCommentRequest(comment))
        }
    }

    private fun addComment(commentRequest: PostCommentRequest) {
        Log.d("Add Comment Detail", "function exec")
        viewModelScope.launch {
            commentUseCase.postCommentUseCase(postId, commentRequest)
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
                            successAddComment(result.data?.commentId!!.toInt())
                            Log.d("Add Comment Detail", "add comment success")
                        }
                        else -> {
                            showToast(result.message.toString())
                        }
                    }
                }
        }
    }

    private fun updateComment(commentRequest: PutCommentRequest, commentId: Int){
        Log.d("Update Comment", "function exec")
        viewModelScope.launch {
            commentUseCase.putCommentUseCase(commentId, commentRequest)
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
                            successAddComment(result.data?.commentId!!.toInt())
                            Log.d("Add Comment Detail", "add comment success")
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
    data class SuccessAddComment(val commentId: Int) : PostDetailActivityState()
    data class IsUpdateComment(val isUpdateComment: Boolean, var commentId: Int) : PostDetailActivityState()
}
