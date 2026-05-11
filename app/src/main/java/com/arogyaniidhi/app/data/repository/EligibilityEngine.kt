package com.arogyaniidhi.app.data.repository

import com.arogyaniidhi.app.data.model.*

object EligibilityEngine {

    fun getEligibleSchemes(profile: FamilyProfile): List<HealthScheme> {
        val eligible = mutableListOf<HealthScheme>()

        // --- AYUSHMAN BHARAT - PM-JAY ---
        if (isEligibleForPMJAY(profile)) {
            eligible.add(
                HealthScheme(
                    id = "pmjay",
                    name = "Ayushman Bharat – PM-JAY",
                    description = "Provides health cover of ₹5 lakh per family per year for secondary and tertiary care hospitalisation.",
                    maxCoverageAmount = 500000,
                    eligibilityCriteria = "BPL families, SECC-listed households, annual income < ₹1.8 lakh",
                    documentsRequired = listOf(
                        "Aadhaar Card (all family members)",
                        "BPL / Ration Card",
                        "Income Certificate (< ₹1.8 lakh/year)",
                        "Ayushman Bharat Card (if already enrolled)",
                        "Passport-size photographs"
                    ),
                    benefits = listOf(
                        "₹5 lakh/year hospitalisation cover",
                        "Pre and post hospitalisation expenses",
                        "1,350+ medical packages covered",
                        "Cashless treatment at empanelled hospitals"
                    ),
                    category = SchemeCategory.CENTRAL
                )
            )
        }

        // --- CENTRAL GOVERNMENT HEALTH SCHEME (CGHS) ---
        if (profile.occupation == OccupationType.GOVERNMENT_EMPLOYEE) {
            eligible.add(
                HealthScheme(
                    id = "cghs",
                    name = "Central Government Health Scheme (CGHS)",
                    description = "Comprehensive healthcare for Central Government employees and pensioners.",
                    maxCoverageAmount = 0, // Unlimited OPD + IPD
                    eligibilityCriteria = "Central Government employee or pensioner and their dependants",
                    documentsRequired = listOf(
                        "CGHS Card / Employee ID",
                        "Aadhaar Card",
                        "Service Certificate from Department",
                        "Dependent family proof (marriage/birth certificate)"
                    ),
                    benefits = listOf(
                        "OPD consultation at CGHS Wellness Centres",
                        "Medicines at subsidised rates",
                        "Specialist referral and hospitalisation",
                        "Reimbursement for private hospitals"
                    ),
                    category = SchemeCategory.CENTRAL
                )
            )
        }

        // --- PRADHAN MANTRI SURAKSHA BIMA YOJANA ---
        if (profile.annualIncome in 1..300000 && profile.occupation != OccupationType.GOVERNMENT_EMPLOYEE) {
            eligible.add(
                HealthScheme(
                    id = "pmsby",
                    name = "Pradhan Mantri Suraksha Bima Yojana",
                    description = "Accident insurance cover of ₹2 lakh at just ₹20/year premium.",
                    maxCoverageAmount = 200000,
                    eligibilityCriteria = "Age 18–70 years, bank account holder",
                    documentsRequired = listOf(
                        "Aadhaar Card",
                        "Bank Account Passbook",
                        "Mobile number linked to bank"
                    ),
                    benefits = listOf(
                        "₹2 lakh accidental death/disability cover",
                        "Premium only ₹20/year",
                        "Auto-debit from bank account"
                    ),
                    category = SchemeCategory.CENTRAL
                )
            )
        }

        // --- JANANI SURAKSHA YOJANA ---
        if (isEligibleForJSY(profile)) {
            eligible.add(
                HealthScheme(
                    id = "jsy",
                    name = "Janani Suraksha Yojana (JSY)",
                    description = "Cash assistance to pregnant women for institutional delivery.",
                    maxCoverageAmount = 1400,
                    eligibilityCriteria = "BPL pregnant women, age 19+, up to 2 live births",
                    documentsRequired = listOf(
                        "Aadhaar Card",
                        "BPL Card / Ration Card",
                        "Mother & Child Protection Card",
                        "Bank Passbook for DBT"
                    ),
                    benefits = listOf(
                        "₹1,400 cash incentive (rural)",
                        "₹1,000 cash incentive (urban)",
                        "Free delivery at government hospital",
                        "ASHA worker assistance"
                    ),
                    category = SchemeCategory.CENTRAL
                )
            )
        }

        // --- RASHTRIYA SWASTHYA BIMA YOJANA (RSBY) ---
        if (isEligibleForRSBY(profile)) {
            eligible.add(
                HealthScheme(
                    id = "rsby",
                    name = "Rashtriya Swasthya Bima Yojana (RSBY)",
                    description = "Smart card-based cashless health insurance for BPL families.",
                    maxCoverageAmount = 30000,
                    eligibilityCriteria = "BPL family with valid BPL card",
                    documentsRequired = listOf(
                        "BPL Card",
                        "Aadhaar Card",
                        "Passport-size photographs (all members)",
                        "Proof of residence"
                    ),
                    benefits = listOf(
                        "₹30,000/year hospitalisation cover",
                        "Family floater (up to 5 members)",
                        "Cashless treatment",
                        "Transport allowance ₹100/visit"
                    ),
                    category = SchemeCategory.CENTRAL
                )
            )
        }

        // --- SENIOR CITIZEN HEALTH INSURANCE SCHEME ---
        if (profile.hasSeniorCitizen) {
            eligible.add(
                HealthScheme(
                    id = "schis",
                    name = "Senior Citizen Health Insurance Scheme",
                    description = "Subsidised health insurance for citizens aged 60 and above.",
                    maxCoverageAmount = 100000,
                    eligibilityCriteria = "Senior citizen (60+) in the family",
                    documentsRequired = listOf(
                        "Aadhaar Card",
                        "Age proof (Birth Certificate / Passport)",
                        "Income Certificate",
                        "Bank Passbook"
                    ),
                    benefits = listOf(
                        "₹1 lakh cover for senior members",
                        "Subsidised premium",
                        "Pre-existing disease coverage after 1 year",
                        "Cashless at network hospitals"
                    ),
                    category = SchemeCategory.STATE
                )
            )
        }

        // --- DISABILITY SUPPORT SCHEME ---
        if (profile.hasDisabledMember) {
            eligible.add(
                HealthScheme(
                    id = "dds",
                    name = "Assistance to Disabled Persons (ADIP)",
                    description = "Government aid for medical aids and appliances for persons with disability.",
                    maxCoverageAmount = 20000,
                    eligibilityCriteria = "Person with ≥40% disability, family income < ₹2 lakh/year",
                    documentsRequired = listOf(
                        "Disability Certificate (≥40%)",
                        "Aadhaar Card",
                        "Income Certificate",
                        "Medical Prescription for aids/appliances",
                        "Bank Passbook"
                    ),
                    benefits = listOf(
                        "Free aids/appliances up to ₹20,000",
                        "Assistive devices (wheelchair, hearing aid)",
                        "Rehabilitation support"
                    ),
                    category = SchemeCategory.CENTRAL
                )
            )
        }

        return eligible
    }

    private fun isEligibleForPMJAY(profile: FamilyProfile): Boolean {
        return profile.hasBplCard ||
               profile.annualIncome <= 180000 ||
               profile.occupation == OccupationType.DAILY_WAGE ||
               profile.occupation == OccupationType.FARMER ||
               profile.occupation == OccupationType.UNEMPLOYED
    }

    private fun isEligibleForJSY(profile: FamilyProfile): Boolean {
        return (profile.hasBplCard || profile.annualIncome <= 100000)
    }

    private fun isEligibleForRSBY(profile: FamilyProfile): Boolean {
        return profile.hasBplCard || profile.annualIncome <= 100000
    }
}
