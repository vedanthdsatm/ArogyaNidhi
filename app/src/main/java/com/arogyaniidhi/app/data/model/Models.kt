package com.arogyaniidhi.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class FamilyProfile(
    val annualIncome: Int,          // in rupees
    val occupation: OccupationType,
    val hasBplCard: Boolean,
    val hasAyushmanCard: Boolean,
    val stateOfResidence: String,
    val familySize: Int,
    val hasDisabledMember: Boolean,
    val hasSeniorCitizen: Boolean    // 60+
)

enum class OccupationType(val displayName: String) {
    FARMER("Farmer / Agricultural Worker"),
    DAILY_WAGE("Daily Wage / Unorganised Labour"),
    GOVERNMENT_EMPLOYEE("Government Employee"),
    PRIVATE_EMPLOYEE("Private Sector Employee"),
    SELF_EMPLOYED("Self Employed / Small Business"),
    UNEMPLOYED("Unemployed / No Income")
}

@Parcelize
data class HealthScheme(
    val id: String,
    val name: String,
    val description: String,
    val maxCoverageAmount: Int,
    val eligibilityCriteria: String,
    val documentsRequired: List<String>,
    val benefits: List<String>,
    val category: SchemeCategory
) : Parcelable

enum class SchemeCategory(val displayName: String) {
    CENTRAL("Central Government"),
    STATE("State Government"),
    INSURANCE("Health Insurance")
}

@Parcelize
data class Hospital(
    val id: String,
    val name: String,
    val address: String,
    val district: String,
    val state: String,
    val phone: String,
    val acceptedSchemes: List<String>,
    val specialties: List<String>,
    val isGovernment: Boolean
) : Parcelable

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

data class PolicyNews(
    val id: String,
    val title: String,
    val summary: String,
    val category: String,
    val source: String,
    val updatedOn: String
)
