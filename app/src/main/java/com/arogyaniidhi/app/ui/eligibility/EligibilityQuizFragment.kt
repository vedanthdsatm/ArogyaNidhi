package com.arogyaniidhi.app.ui.eligibility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.arogyaniidhi.app.R
import com.arogyaniidhi.app.data.model.OccupationType
import com.arogyaniidhi.app.databinding.FragmentEligibilityQuizBinding

class EligibilityQuizFragment : Fragment() {

    private var _binding: FragmentEligibilityQuizBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EligibilityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEligibilityQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentStep.observe(viewLifecycleOwner) { step ->
            showStep(step)
            updateProgressIndicator(step)
        }

        binding.btnNext.setOnClickListener { handleNext() }
        binding.btnBack.setOnClickListener { viewModel.previousStep() }

        // Occupation spinner
        val occupations = OccupationType.values().map { it.displayName }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, occupations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOccupation.adapter = adapter
    }

    private fun showStep(step: Int) {
        // Hide all cards
        binding.cardStep1.visibility = View.GONE
        binding.cardStep2.visibility = View.GONE
        binding.cardStep3.visibility = View.GONE
        binding.cardStep4.visibility = View.GONE
        binding.cardStep5.visibility = View.GONE

        binding.btnBack.visibility = if (step == 0) View.INVISIBLE else View.VISIBLE
        binding.btnNext.text = if (step == viewModel.totalSteps - 1) "Check Eligibility" else "Next"

        when (step) {
            0 -> binding.cardStep1.visibility = View.VISIBLE
            1 -> binding.cardStep2.visibility = View.VISIBLE
            2 -> binding.cardStep3.visibility = View.VISIBLE
            3 -> binding.cardStep4.visibility = View.VISIBLE
            4 -> binding.cardStep5.visibility = View.VISIBLE
        }
    }

    private fun updateProgressIndicator(step: Int) {
        binding.stepIndicator.text = "Step ${step + 1} of ${viewModel.totalSteps}"
        binding.progressBar.progress = ((step + 1) * 100) / viewModel.totalSteps
        binding.tvStepTitle.text = getStepTitle(step)
    }

    private fun getStepTitle(step: Int): String = when (step) {
        0 -> "💰 Annual Family Income"
        1 -> "👔 Occupation & Employment"
        2 -> "📋 Documents & Cards"
        3 -> "👨‍👩‍👧‍👦 Family Details"
        4 -> "🏠 Location"
        else -> ""
    }

    private fun handleNext() {
        val step = viewModel.currentStep.value ?: 0
        when (step) {
            0 -> {
                val incomeStr = binding.etIncome.text.toString()
                if (incomeStr.isEmpty()) {
                    Toast.makeText(context, "Please enter your annual income", Toast.LENGTH_SHORT).show()
                    return
                }
                viewModel.annualIncome = incomeStr.toIntOrNull() ?: 0
                viewModel.nextStep()
            }
            1 -> {
                val selectedIndex = binding.spinnerOccupation.selectedItemPosition
                viewModel.occupation = OccupationType.values()[selectedIndex]
                viewModel.nextStep()
            }
            2 -> {
                viewModel.hasBplCard = binding.cbBplCard.isChecked
                viewModel.hasAyushmanCard = binding.cbAyushmanCard.isChecked
                viewModel.nextStep()
            }
            3 -> {
                val sizeStr = binding.etFamilySize.text.toString()
                if (sizeStr.isEmpty()) {
                    Toast.makeText(context, "Please enter family size", Toast.LENGTH_SHORT).show()
                    return
                }
                viewModel.familySize = sizeStr.toIntOrNull() ?: 1
                viewModel.hasDisabledMember = binding.cbDisabled.isChecked
                viewModel.hasSeniorCitizen = binding.cbSenior.isChecked
                viewModel.nextStep()
            }
            4 -> {
                val state = binding.etState.text.toString()
                if (state.isEmpty()) {
                    Toast.makeText(context, "Please enter your state", Toast.LENGTH_SHORT).show()
                    return
                }
                viewModel.stateOfResidence = state
                viewModel.calculateEligibility()
                findNavController().navigate(R.id.action_eligibilityQuizFragment_to_resultFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
