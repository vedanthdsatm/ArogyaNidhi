package com.arogyaniidhi.app.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arogyaniidhi.app.data.repository.NewsRepository
import com.arogyaniidhi.app.databinding.FragmentNewsUpdatesBinding

class NewsUpdatesFragment : Fragment() {

    private var _binding: FragmentNewsUpdatesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsUpdatesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewsAdapter()
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.adapter = adapter

        binding.btnRefresh.setOnClickListener {
            adapter.submitList(NewsRepository.getLatestUpdates())
        }

        adapter.submitList(NewsRepository.getLatestUpdates())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
