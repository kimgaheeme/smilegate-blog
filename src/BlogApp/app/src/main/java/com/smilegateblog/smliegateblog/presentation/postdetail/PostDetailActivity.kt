package com.smilegateblog.smliegateblog.presentation.postdetail

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smilegateblog.smliegateblog.R
import com.smilegateblog.smliegateblog.data.dto.comment.GetCommentsResponseItem
import com.smilegateblog.smliegateblog.data.dto.comment.PostCommentRequest
import com.smilegateblog.smliegateblog.data.dto.comment.PutCommentRequest
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.post.GetPostResponse
import com.smilegateblog.smliegateblog.databinding.ActivityPostDetailBinding
import com.smilegateblog.smliegateblog.databinding.BottomSheetBinding
import com.smilegateblog.smliegateblog.presentation.GetCommentsResponseItemdetail.CommentAdapter
import com.smilegateblog.smliegateblog.presentation.GetCommentsResponseItemdetail.OnCommentClickListener
import com.smilegateblog.smliegateblog.presentation.common.isEmail
import com.smilegateblog.smliegateblog.presentation.common.visible
import com.smilegateblog.smliegateblog.presentation.editpost.EditPostActivity
import com.smilegateblog.smliegateblog.presentation.main.home.OnItemClickListener
import com.smilegateblog.smliegateblog.util.setImageUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailActivity : AppCompatActivity(), OnCommentClickListener<Int> {

    private lateinit var binding : ActivityPostDetailBinding
    private val viewModel : PostDetailViewModel by viewModels()
    private val mAdapter = CommentAdapter(this)


    private lateinit var btnSheetBinding :BottomSheetBinding
    private lateinit var dialog : BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        btnSheetBinding = BottomSheetBinding.inflate(inflater)
        setContentView(binding.root)
        dialog = BottomSheetDialog(this)
        dialog.setContentView(btnSheetBinding.root)

        observePostDetail()
        setupCommentRecyclerView()
        //setCommentSendBtnVisible()
        addComment()
        onToggleBtnClicked()
        onUpdateBtnClicked()
        onDeleteBtnClicked()
        handlerBottomSheet()
        clearScreen()

    }

    private fun onDeleteBtnClicked() {
        btnSheetBinding.btnDeletePost.setOnClickListener {
            viewModel.deletePost()
            finish()
        }
    }

    private fun handlerBottomSheet(){
        binding.btnDialog.setOnClickListener{
            dialog.show()
        }
    }

    private fun clearScreen(){
        binding.btnBack.setOnClickListener {
            finish()
        }
    }


    private fun setupPostDetail(postDetail: GetPostResponse){
        if(postDetail.isScrap){
            binding.tbtnScrap.isChecked = true
            binding.tbtnScrap.setBackgroundResource(R.drawable.ic_baseline_turned_in_24)
        }else{
            binding.tbtnScrap.isChecked = false
            binding.tbtnScrap.setBackgroundResource(R.drawable.ic_baseline_turned_in_not_24)
        }
        binding.ivPostImage.setImageUrl(url = postDetail.postImageId, placeHolder = null, 1)
        binding.tvPostTitle.setText(postDetail.title)
        binding.tvPostNickname.setText(postDetail.nickname)
        binding.tvPostContent.setText(postDetail.content)
    }

    private fun observePostDetail() {
        viewModel.postDetail.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { product ->
                product?.let { setupPostDetail(it) }
            }
            .launchIn(lifecycleScope)
    }

    private fun setupCommentRecyclerView(){

        binding.listCommentItem.apply {
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(this.context)
        }

        lifecycleScope.launch {
            viewModel.comment.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }



    private fun addComment(){
        binding.btnSendComment.setOnClickListener {
            val comment = binding.etComment.text.toString().trim()
            if(comment.isNotEmpty()) {
                viewModel.sendComment(comment)
                binding.etComment.setText("")
                mAdapter.refresh()
            }
        }
    }

    private fun onToggleBtnClicked(){
        binding.tbtnScrap.setOnClickListener {
            if(binding.tbtnScrap.isChecked){
                addScrap()
                binding.tbtnScrap.isChecked = true
                binding.tbtnScrap.setBackgroundResource(R.drawable.ic_baseline_turned_in_24)
            }else{
                delScrap()
                binding.tbtnScrap.isChecked = false
                binding.tbtnScrap.setBackgroundResource(R.drawable.ic_baseline_turned_in_not_24)
            }
        }
    }

    private fun addScrap() {
        viewModel.addScrap()
    }

    private fun onUpdateBtnClicked() {
        btnSheetBinding.btnUpdatePost.setOnClickListener {
            val intent = Intent(this, EditPostActivity::class.java)
            intent.putExtra("postId", viewModel.postId)
            startActivity(intent)
            finish()
        }
    }

    private fun delScrap() {
        viewModel.delScrap()
    }

    private fun setCommentSendBtnVisible(){
        val comment = binding.etComment.text.toString().trim()
        binding.btnSendComment.visibility = if(comment.isEmpty()) View.GONE else View.VISIBLE
    }

    override fun onDeleteCommentClicked(item: Int?) {
        if(item != null) viewModel.deleteComment(item)
    }

    override fun onUpdateCommentClicked(item: GetCommentsResponseItem) {
        binding.etComment.setText(item!!.content)
        viewModel.setIsUpdateComment(item!!.commentId)
    }

}