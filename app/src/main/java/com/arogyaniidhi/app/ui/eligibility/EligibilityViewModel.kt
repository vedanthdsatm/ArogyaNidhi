package com.arogyaniidhi.app.ui.eligibility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arogyaniidhi.app.data.model.*
import com.arogyaniidhi.app.data.repository.EligibilityEngine

class EligibilityViewModel : ViewModel() {

    // Step tracking
    private val _currentStep = MutableLiveData(0)
    val currentStep: LiveData<Int> = _currentStep

    val totalSteps = 5

    // Quiz answers
    var annualIncome: Int = 0
    var occupation: OccupationType = OccupationType.DAILY_WAGE
    var hasBplCard: Boolean = false
    var hasAyushmanCard: Boolean = false
    var familySize: Int = 1
    var hasDisabledMember: Boolean = false
    var hasSeniorCitizen: Boolean = false
    var stateOfResidence: String = ""

    // Results
    private val _eligibleSchemes = MutableLiveData<List<HealthScheme>>()
    val eligibleSchemes: LiveData<List<HealthScheme>> = _eligibleSchemes

    fun nextStep() {
        val current = _currentStep.value ?: 0
        if (current < totalSteps - 1) {
            _currentStep.value = current + 1
        }
    }

    fun previousStep() {
        val current = _currentStep.value ?: 0
        if (current > 0) {
            _currentStep.value = current - 1
        }
    }

    fun calculateEligibility() {
        val profile = FamilyProfile(
            annualIncome = annualIncome,
            occupation = occupation,
            hasBplCard = hasBplCard,
            hasAyushmanCard = hasAyushmanCard,
            stateOfResidence = stateOfResidence,
            familySize = familySize,
            hasDisabledMember = hasDisabledMember,
            hasSeniorCitizen = hasSeniorCitizen
        )
        _eligibleSchemes.value = EligibilityEngine.getEligibleSchemes(profile)
    }

    fun resetQuiz() {
        _currentStep.value = 0
        annualIncome = 0
        occupation = OccupationType.DAILY_WAGE
        hasBplCard = false
        hasAyushmanCard = false
        familySize = 1
        hasDisabledMember = false
        hasSeniorCitizen = false
        stateOfResidence = ""
        _eligibleSchemes.value = emptyList()
    }
}
