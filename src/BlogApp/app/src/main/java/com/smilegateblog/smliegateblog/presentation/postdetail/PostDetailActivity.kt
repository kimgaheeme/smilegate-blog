package com.smilegateblog.smliegateblog.presentation.postdetail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilegateblog.smliegateblog.databinding.ActivityPostDetailBinding
import com.smilegateblog.smliegateblog.presentation.GetCommentsResponseItemdetail.CommentAdapter
import com.smilegateblog.smliegateblog.presentation.GetCommentsResponseItemdetail.OnCommentClickListener
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCommentRecyclerView()

        binding.btnDeletePost.setOnClickListener {
            deletePost()
        }
    }

    private fun setupCommentRecyclerView(){
        val mAdapter = CommentAdapter(this)

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

    override fun onDeleteCommentClicked(item: Int?) {
        if(item != null) viewModel.deleteComment(item)
    }


}