package com.smilegateblog.smliegateblog.presentation.main.scrap

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilegateblog.smliegateblog.databinding.FragmentScrapBinding
import com.smilegateblog.smliegateblog.presentation.main.home.PostAdapter
import com.smilegateblog.smliegateblog.presentation.main.home.HomeViewModel
import com.smilegateblog.smliegateblog.presentation.main.home.OnItemClickListener
import com.smilegateblog.smliegateblog.presentation.postdetail.PostDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScrapFragment : Fragment(), OnItemClickListener<Int> {

    private var _binding: FragmentScrapBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScrapViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScrapBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupScrapPostRecyclerView()

        return root
    }

    private fun setupScrapPostRecyclerView(){
        val mAdapter = PostAdapter(this)

        binding.listScrapItem.apply {
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            viewModel.scrapPost.collectLatest {
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