package com.smilegateblog.smliegateblog.presentation.editpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.smilegateblog.smliegateblog.R
import com.smilegateblog.smliegateblog.data.dto.post.GetPostResponse
import com.smilegateblog.smliegateblog.databinding.ActivityEditPostBinding
import com.smilegateblog.smliegateblog.databinding.ActivityPostDetailBinding
import com.smilegateblog.smliegateblog.presentation.GetCommentsResponseItemdetail.CommentAdapter
import com.smilegateblog.smliegateblog.presentation.common.isEmail
import com.smilegateblog.smliegateblog.presentation.postdetail.PostDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class EditPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditPostBinding
    private val viewModel: EditPostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getPostDetail()
        onBtnClicked()
        observeProduct()
    }

    private fun observeProduct() {
        viewModel.postDetail.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { product ->
                product?.let { setupPostDetail(it) }
            }
            .launchIn(lifecycleScope)
    }

    private fun onBtnClicked() {
        binding.btnPostPost.setOnClickListener {
            if(viewModel.isUpdate) updatePost()
            else postPost()
        }
    }


    private fun updatePost() {
        val content = binding.etContent.text.toString()
        val title = binding.etTitle.text.toString()
        if(validate(content, title)){
            viewModel.updatePost(content = content, title = title)
        }
    }

    private fun postPost() {
        val content = binding.etContent.text.toString()
        val title = binding.etTitle.text.toString()
        if(validate(content, title)){
            viewModel.postPost(content = content, title = title)
        }
    }


    private fun setupPostDetail(postdetail: GetPostResponse) {
        binding.etContent.setText(postdetail.content)
        binding.etTitle.setText(postdetail.title)
    }

//    private fun setEmailError(e: String?){
//        binding.emailInput.error = e
//    }
//
//    private fun setNicknameError(e: String?){
//        binding.nicknameInput.error = e
//    }
//
//    private fun resetAllError(){
//        setEmailError(null)
//        setNicknameError(null)
//    }


    private fun validate(content: String, title: String): Boolean {
//        resetAllError()

        if (!title.isEmpty()) {
//            setEmailError(getString(R.string.error_title_not_valid))
            return false
        }

        if (!content.isEmpty()) {
//            setEmailError(getString(R.string.error_content_not_valid))
            return false
        }


        return true
    }
}