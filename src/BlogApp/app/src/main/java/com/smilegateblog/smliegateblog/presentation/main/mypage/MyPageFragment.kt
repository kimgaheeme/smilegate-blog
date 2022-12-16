package com.smilegateblog.smliegateblog.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilegateblog.smliegateblog.databinding.FragmentMypageBinding
import com.smilegateblog.smliegateblog.presentation.main.home.PostAdapter
import com.smilegateblog.smliegateblog.presentation.main.home.OnItemClickListener
import com.smilegateblog.smliegateblog.presentation.postdetail.PostDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPageFragment : Fragment(), OnItemClickListener<Int> {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupMyPostRecyclerView()
        return root
    }

    private fun setupMyPostRecyclerView(){
        val mAdapter = PostAdapter(this)

        binding.listScrapItem.apply {
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            viewModel.myPost.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
    
    override fun onItemClicked(postId: Int?) {
        val intent = Intent(activity, PostDetailActivity::class.java)
        intent.putExtra("postId", postId)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}