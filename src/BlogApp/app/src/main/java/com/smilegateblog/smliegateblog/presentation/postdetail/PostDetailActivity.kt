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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PostDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPostDetailBinding
    private val viewModel : PostDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCommentRecyclerView()
    }

    private fun setupCommentRecyclerView(){
        val mAdapter = CommentAdapter()

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


}