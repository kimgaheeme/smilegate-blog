package com.smilegateblog.smliegateblog.presentation.editpost

import android.content.Context
import android.icu.text.CaseMap.Title
import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smilegateblog.smliegateblog.data.dto.post.GetPostResponse
import com.smilegateblog.smliegateblog.data.dto.post.PostPostRequest
import com.smilegateblog.smliegateblog.data.dto.post.PutPostRequest
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.usecase.CommentUseCase
import com.smilegateblog.smliegateblog.domain.usecase.PostUseCase
import com.smilegateblog.smliegateblog.presentation.login.LoginActivityState
import com.smilegateblog.smliegateblog.util.Resource
import com.smilegateblog.smliegateblog.util.UriUtil.toFile
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPostViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val postUseCase: PostUseCase,
    @ApplicationContext val context: Context
) : ViewModel() {

    var postId = handle.get<Int>("postId")?: 0
    var isUpdate = postId != 0
    private lateinit var imageUri:Uri


    private val _postDetail = MutableStateFlow(GetPostResponse())
    val postDetail : StateFlow<GetPostResponse> get() = _postDetail

    
    private val _state = MutableStateFlow<EditPostActivityState>(EditPostActivityState.Init)
    val state: StateFlow<EditPostActivityState> get() = _state

    fun setImage(uri: Uri){
        imageUri = uri
    }

    fun setContentText(text: String) {
        _postDetail.update {
            it.copy(content = text)
        }
    }

    fun setTitleText(text: String) {
        _postDetail.update {
            it.copy(title = text)
        }
    }

    private fun setLoading(){
        _state.value = EditPostActivityState.IsLoading(true)
    }
    

    private fun hideLoading(){
        _state.value = EditPostActivityState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = EditPostActivityState.ShowToast(message)
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
    
    private fun successPost(postId: Int) {
        _state.value = EditPostActivityState.SuccessPost(postId = postId)
    }

    private fun errorPost(message: String) {
        _state.value = EditPostActivityState.ErrorPost(message)
    }

    fun updatePost(content: String, title: String) {
        var image = toFile(context, imageUri)
        viewModelScope.launch {
            postUseCase.putPostUseCase.invoke(PutPostRequest(title = title, content = content, postImage = ""), postid = postId, image)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.stackTraceToString())

                }
                .collect { result ->
                    hideLoading()
                    Log.d("update post", "update post")
                    when(result) {
                        is Resource.Success -> {
                            successPost(result.data!!.postId)
                        }
                        is Resource.Error -> errorPost(result.message?: "error in login function of loginViewModel")
                        else -> setLoading()
                    }

                }
        }
    }

    fun postPost(content: String, title: String) {
        var image = toFile(context, imageUri)
        viewModelScope.launch {
            postUseCase.postPostUseCase.invoke(PostPostRequest(title = title, content = content, postImage = "", type = "plain"), image)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.stackTraceToString())

                }
                .collect { result ->
                    hideLoading()
                    when(result) {
                        is Resource.Success -> {
                            successPost(result.data!!.postId)
                        }
                        is Resource.Error -> errorPost(result.message?: "error in login function of loginViewModel")
                        else -> setLoading()
                    }

                }
        }
    }



}

sealed class EditPostActivityState {
    object Init : EditPostActivityState()
    data class IsLoading(val isLoading: Boolean) : EditPostActivityState()
    data class ShowToast(val message: String) : EditPostActivityState()
    data class SuccessPost(val postId: Int) : EditPostActivityState()
    data class ErrorPost(val rawResponse: String) : EditPostActivityState()
}
