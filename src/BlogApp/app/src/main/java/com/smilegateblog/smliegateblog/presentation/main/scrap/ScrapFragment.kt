package com.smilegateblog.smliegateblog.presentation.main.scrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilegateblog.smliegateblog.databinding.FragmentScrapBinding
import com.smilegateblog.smliegateblog.presentation.main.home.HomeRecentPostAdapter
import com.smilegateblog.smliegateblog.presentation.main.home.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ScrapFragment : Fragment() {

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


        return root
    }

    private fun setupScrapPostRecyclerView(){
        val mAdapter = ScrapPostAdapter()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}