package com.arogyaniidhi.app.data.repository

import com.arogyaniidhi.app.data.model.PolicyNews

object NewsRepository {

    fun getLatestUpdates(): List<PolicyNews> {
        return listOf(
            PolicyNews(
                id = "n1",
                title = "Ayushman Bharat Digital Mission integration expanded",
                summary = "More states have expanded ABDM-linked digital health IDs for faster hospital onboarding and claim flow.",
                category = "Digital Health",
                source = "MoHFW",
                updatedOn = "May 2026"
            ),
            PolicyNews(
                id = "n2",
                title = "District-level health insurance enrollment drive launched",
                summary = "Government health workers have started focused enrollment camps in underserved rural and urban clusters.",
                category = "Scheme Enrollment",
                source = "National Health Authority",
                updatedOn = "May 2026"
            ),
            PolicyNews(
                id = "n3",
                title = "Maternal and child health package revisions announced",
                summary = "Updated maternal and neonatal care packages now include expanded diagnostics in empanelled hospitals.",
                category = "Maternal Health",
                source = "NHM",
                updatedOn = "April 2026"
            ),
            PolicyNews(
                id = "n4",
                title = "Government-private referral protocols strengthened",
                summary = "New referral norms improve coordination between district hospitals and private empanelled providers.",
                category = "Hospital Network",
                source = "State Health Departments",
                updatedOn = "April 2026"
            ),
            PolicyNews(
                id = "n5",
                title = "NCD screening in primary centers scaled up",
                summary = "Current affairs update: screening for diabetes, hypertension and oral cancer is being expanded in high-risk districts.",
                category = "Current Affairs",
                source = "Public Health Bulletin",
                updatedOn = "March 2026"
            )
        )
    }
}
