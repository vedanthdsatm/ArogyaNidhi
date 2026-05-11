package com.arogyaniidhi.app.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arogyaniidhi.app.R
import com.arogyaniidhi.app.databinding.FragmentResultBinding
import com.arogyaniidhi.app.ui.eligibility.EligibilityViewModel

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EligibilityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SchemeResultAdapter { scheme ->
            val bundle = android.os.Bundle().apply { putParcelable("scheme", scheme) }
            findNavController().navigate(R.id.action_resultFragment_to_documentGuideFragment, bundle)
        }

        binding.rvSchemes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSchemes.adapter = adapter

        viewModel.eligibleSchemes.observe(viewLifecycleOwner) { schemes ->
            if (schemes.isEmpty()) {
                binding.tvResultHeader.text = "No schemes found"
                binding.tvResultSubtitle.text = "Based on your inputs, we couldn't find matching government schemes. Try updating your details."
                binding.rvSchemes.visibility = View.GONE
                binding.tvNoResult.visibility = View.VISIBLE
            } else {
                binding.tvResultHeader.text = "🎉 You qualify for ${schemes.size} scheme${if (schemes.size > 1) "s" else ""}!"
                binding.tvResultSubtitle.text = "Tap on any scheme to see the documents you need to apply."
                binding.rvSchemes.visibility = View.VISIBLE
                binding.tvNoResult.visibility = View.GONE
                adapter.submitList(schemes)
            }
        }

        binding.btnRetake.setOnClickListener {
            viewModel.resetQuiz()
            findNavController().navigate(R.id.action_resultFragment_to_eligibilityQuizFragment)
        }

        binding.btnFindHospitals.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_hospitalListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
