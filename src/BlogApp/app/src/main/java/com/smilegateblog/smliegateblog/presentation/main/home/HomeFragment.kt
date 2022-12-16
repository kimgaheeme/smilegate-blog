package com.smilegateblog.smliegateblog.presentation.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.addRepeatingJob
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilegateblog.smliegateblog.databinding.FragmentHomeBinding
import com.smilegateblog.smliegateblog.presentation.main.MainActivity
import com.smilegateblog.smliegateblog.presentation.postdetail.PostDetailActivity
import com.smilegateblog.smliegateblog.presentation.postdetail.PostDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), OnItemClickListener<String> {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupRecentPostRecyclerView()

        return root
    }

    private fun setupRecentPostRecyclerView(){
        val mAdapter = PostAdapter(this)

        binding.listRecentPostItem.apply {
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            viewModel.recentPost.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    override fun onItemClicked(postId: String?) {
        val intent = Intent(activity, PostDetailActivity::class.java)
        intent.putExtra("postId", postId)
        startActivity(intent)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}