package com.arogyaniidhi.app.ui.hospitals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arogyaniidhi.app.data.model.Hospital
import com.arogyaniidhi.app.databinding.ItemHospitalBinding

class HospitalAdapter : ListAdapter<Hospital, HospitalAdapter.HospitalViewHolder>(DiffCallback()) {

    inner class HospitalViewHolder(private val binding: ItemHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hospital: Hospital) {
            binding.tvHospitalName.text = hospital.name
            binding.tvAddress.text = "${hospital.address}, ${hospital.district}, ${hospital.state}"
            binding.tvPhone.text = "📞 ${hospital.phone}"
            binding.tvType.text = if (hospital.isGovernment) "🏥 Government" else "🏨 Private"
            binding.tvSpecialties.text = "Specialties: ${hospital.specialties.joinToString(", ")}"
            binding.tvSchemes.text = "Schemes: ${hospital.acceptedSchemes.size} accepted"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val binding = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HospitalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Hospital>() {
        override fun areItemsTheSame(oldItem: Hospital, newItem: Hospital) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Hospital, newItem: Hospital) = oldItem == newItem
    }
}
