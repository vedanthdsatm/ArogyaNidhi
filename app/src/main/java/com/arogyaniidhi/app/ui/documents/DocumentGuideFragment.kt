package com.arogyaniidhi.app.ui.documents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.arogyaniidhi.app.R
import com.arogyaniidhi.app.databinding.FragmentDocumentGuideBinding

class DocumentGuideFragment : Fragment() {

    private var _binding: FragmentDocumentGuideBinding? = null
    private val binding get() = _binding!!
    private val args: DocumentGuideFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDocumentGuideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scheme = args.scheme

        binding.tvSchemeName.text = scheme.name
        binding.tvEligibilityCriteria.text = scheme.eligibilityCriteria
        
        binding.tvCoverageAmount.text = if (scheme.maxCoverageAmount > 0)
            getString(R.string.max_coverage, formatAmount(scheme.maxCoverageAmount))
        else getString(R.string.coverage_as_entitlement)

        // Documents Checklist
        val documentAdapter = DocumentChecklistAdapter(scheme.documentsRequired)
        binding.rvDocuments.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDocuments.adapter = documentAdapter

        // Benefits list
        val benefitsAdapter = BenefitsAdapter(scheme.benefits)
        binding.rvBenefits.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBenefits.adapter = benefitsAdapter

        binding.tvAllReadyMessage.text =
            getString(R.string.documents_ready_message, scheme.documentsRequired.size)
    }

    private fun formatAmount(amount: Int): String {
        return when {
            amount >= 100000 -> "₹${amount / 100000} Lakh"
            else -> "₹$amount"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
