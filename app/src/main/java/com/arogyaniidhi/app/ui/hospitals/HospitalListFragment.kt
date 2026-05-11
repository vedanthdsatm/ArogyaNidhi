package com.arogyaniidhi.app.ui.hospitals

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arogyaniidhi.app.data.repository.HospitalRepository
import com.arogyaniidhi.app.databinding.FragmentHospitalListBinding

class HospitalListFragment : Fragment() {

    private var _binding: FragmentHospitalListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HospitalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHospitalListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HospitalRepository.ensureLoaded(requireContext())

        adapter = HospitalAdapter()
        binding.rvHospitals.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHospitals.adapter = adapter

        // Load all hospitals initially
        adapter.submitList(HospitalRepository.searchHospitals(""))

        // District autocomplete
        val districts = HospitalRepository.getAllDistricts()
        val districtAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, districts)
        binding.etDistrict.setAdapter(districtAdapter)

        // Search
        binding.btnSearch.setOnClickListener { performSearch() }
        binding.cbGovernment.setOnCheckedChangeListener { _, _ -> performSearch() }
        binding.cbPrivate.setOnCheckedChangeListener { _, _ -> performSearch() }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = performSearch()
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun performSearch() {
        val query = binding.etSearch.text.toString()
        val district = binding.etDistrict.text.toString()
        val showGovernment = binding.cbGovernment.isChecked
        val showPrivate = binding.cbPrivate.isChecked

        val results = HospitalRepository.searchHospitals(query, district).filter { hospital ->
            (showGovernment && hospital.isGovernment) || (showPrivate && !hospital.isGovernment)
        }
        adapter.submitList(results)

        if (results.isEmpty()) {
            binding.tvNoHospital.visibility = View.VISIBLE
            binding.rvHospitals.visibility = View.GONE
        } else {
            binding.tvNoHospital.visibility = View.GONE
            binding.rvHospitals.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
