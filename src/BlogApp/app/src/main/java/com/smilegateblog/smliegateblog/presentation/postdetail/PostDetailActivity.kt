package com.smilegateblog.smliegateblog.presentation.postdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.smilegateblog.smliegateblog.databinding.ActivityLoginBinding
import com.smilegateblog.smliegateblog.databinding.ActivityPostDetailBinding
import com.smilegateblog.smliegateblog.presentation.login.LoginViewModel

class PostDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPostDetailBinding
    private val viewModel : PostDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
    }
}