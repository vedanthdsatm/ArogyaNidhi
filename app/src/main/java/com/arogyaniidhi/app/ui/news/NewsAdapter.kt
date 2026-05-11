package com.arogyaniidhi.app.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arogyaniidhi.app.data.model.PolicyNews
import com.arogyaniidhi.app.databinding.ItemPolicyNewsBinding

class NewsAdapter : ListAdapter<PolicyNews, NewsAdapter.NewsViewHolder>(DiffCallback()) {

    inner class NewsViewHolder(private val binding: ItemPolicyNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: PolicyNews) {
            binding.tvTitle.text = news.title
            binding.tvSummary.text = news.summary
            binding.tvMeta.text = "${news.category} • ${news.source} • ${news.updatedOn}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemPolicyNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<PolicyNews>() {
        override fun areItemsTheSame(oldItem: PolicyNews, newItem: PolicyNews): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: PolicyNews, newItem: PolicyNews): Boolean = oldItem == newItem
    }
}
