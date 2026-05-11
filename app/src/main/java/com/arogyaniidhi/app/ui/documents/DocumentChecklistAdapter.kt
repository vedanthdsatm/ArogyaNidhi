package com.arogyaniidhi.app.ui.documents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arogyaniidhi.app.databinding.ItemDocumentChecklistBinding

class DocumentChecklistAdapter(
    private val documents: List<String>
) : RecyclerView.Adapter<DocumentChecklistAdapter.ViewHolder>() {

    private val checkedState = BooleanArray(documents.size) { false }

    inner class ViewHolder(private val binding: ItemDocumentChecklistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(document: String, position: Int) {
            binding.cbDocument.text = document
            binding.cbDocument.isChecked = checkedState[position]
            binding.cbDocument.setOnCheckedChangeListener { _, isChecked ->
                checkedState[position] = isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDocumentChecklistBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(documents[position], position)
    }

    override fun getItemCount() = documents.size
}

class BenefitsAdapter(
    private val benefits: List<String>
) : RecyclerView.Adapter<BenefitsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemDocumentChecklistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(benefit: String) {
            binding.cbDocument.isEnabled = false
            binding.cbDocument.buttonDrawable = null
            binding.cbDocument.text = "✅  $benefit"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDocumentChecklistBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(benefits[position])
    override fun getItemCount() = benefits.size
}
