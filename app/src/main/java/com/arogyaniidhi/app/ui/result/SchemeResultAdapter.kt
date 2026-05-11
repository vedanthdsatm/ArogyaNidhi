package com.arogyaniidhi.app.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arogyaniidhi.app.data.model.HealthScheme
import com.arogyaniidhi.app.data.model.SchemeCategory
import com.arogyaniidhi.app.databinding.ItemSchemeResultBinding

class SchemeResultAdapter(
    private val onSchemeClick: (HealthScheme) -> Unit
) : ListAdapter<HealthScheme, SchemeResultAdapter.SchemeViewHolder>(DiffCallback()) {

    inner class SchemeViewHolder(private val binding: ItemSchemeResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(scheme: HealthScheme) {
            binding.tvSchemeName.text = scheme.name
            binding.tvSchemeDescription.text = scheme.description
            binding.tvCoverage.text = if (scheme.maxCoverageAmount > 0)
                "Coverage: ₹${formatAmount(scheme.maxCoverageAmount)}"
            else
                "Coverage: As per entitlement"

            binding.chipCategory.text = scheme.category.displayName
            binding.chipCategory.setChipBackgroundColorResource(
                when (scheme.category) {
                    SchemeCategory.CENTRAL -> com.google.android.material.R.color.design_default_color_primary
                    SchemeCategory.STATE -> com.google.android.material.R.color.design_default_color_secondary
                    SchemeCategory.INSURANCE -> com.google.android.material.R.color.design_default_color_on_surface
                }
            )

            binding.btnViewDocs.setOnClickListener { onSchemeClick(scheme) }
            binding.root.setOnClickListener { onSchemeClick(scheme) }
        }

        private fun formatAmount(amount: Int): String {
            return when {
                amount >= 100000 -> "${amount / 100000} Lakh"
                else -> amount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchemeViewHolder {
        val binding = ItemSchemeResultBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SchemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchemeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<HealthScheme>() {
        override fun areItemsTheSame(oldItem: HealthScheme, newItem: HealthScheme) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: HealthScheme, newItem: HealthScheme) = oldItem == newItem
    }
}
