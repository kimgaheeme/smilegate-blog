package com.smilegateblog.smliegateblog.presentation.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.smilegateblog.smliegateblog.databinding.FragmentHomeBinding
import com.smilegateblog.smliegateblog.domain.model.Post
import com.smilegateblog.smliegateblog.presentation.postdetail.PostDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(), OnItemClickListener<Int>, OnPostClickListener<Int> {

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
        observeMostViewed()

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

    private fun observeMostViewed(){
        viewModel.mostViewedPost.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { product ->
                product?.let { setupMostViewed(it) }
            }
            .launchIn(lifecycleScope)


    }

    private fun setupMostViewed(data: List<Post>){
        binding.viewpager.adapter = ViewPagerAdapter(data, this)
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

    override fun onPostClicked(item: Int?) {
        val intent = Intent(activity, PostDetailActivity::class.java)
        intent.putExtra("postId", item)
        startActivity(intent)
    }
}