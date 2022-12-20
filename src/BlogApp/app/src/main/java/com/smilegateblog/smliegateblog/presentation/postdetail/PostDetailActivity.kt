package com.smilegateblog.smliegateblog.presentation.postdetail

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilegateblog.smliegateblog.R
import com.smilegateblog.smliegateblog.data.dto.comment.GetCommentsResponseItem
import com.smilegateblog.smliegateblog.data.dto.comment.PostCommentRequest
import com.smilegateblog.smliegateblog.data.dto.comment.PutCommentRequest
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.databinding.ActivityPostDetailBinding
import com.smilegateblog.smliegateblog.presentation.GetCommentsResponseItemdetail.CommentAdapter
import com.smilegateblog.smliegateblog.presentation.GetCommentsResponseItemdetail.OnCommentClickListener
import com.smilegateblog.smliegateblog.presentation.common.isEmail
import com.smilegateblog.smliegateblog.presentation.common.visible
import com.smilegateblog.smliegateblog.presentation.main.home.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailActivity : AppCompatActivity(), OnCommentClickListener<Int> {

    private lateinit var binding : ActivityPostDetailBinding
    private val viewModel : PostDetailViewModel by viewModels()
    private val mAdapter = CommentAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupPostDetail()
        setupCommentRecyclerView()
        //setCommentSendBtnVisible()
        addComment()
        onToggleBtnClicked()

        binding.btnDeletePost.setOnClickListener {
            deletePost()
        }
    }


    private fun setupPostDetail(){
        if(viewModel.postDetail.value.isScrap){
            binding.tbtnScrap.isChecked = true
       }
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

    private fun deletePost(){
        viewModel.deletePost()
        finish()
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
            }else{
                delScrap()
            }
        }
    }

    private fun addScrap() {
        viewModel.addScrap()
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