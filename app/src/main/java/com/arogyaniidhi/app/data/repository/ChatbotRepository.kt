package com.arogyaniidhi.app.data.repository

object ChatbotRepository {

    fun getResponse(question: String): String {
        val q = question.lowercase()
        return when {
            q.contains("ayushman") || q.contains("pmjay") -> {
                "Ayushman Bharat (PM-JAY) provides up to Rs. 5 lakh coverage per family per year for eligible households. Check your eligibility in the app quiz and keep Aadhaar, ration card, and family ID ready."
            }
            q.contains("document") || q.contains("apply") -> {
                "For most schemes, keep Aadhaar card, income certificate, ration/BPL card, and residence proof ready. The exact checklist is shown in each scheme card."
            }
            q.contains("hospital") || q.contains("private") || q.contains("government") -> {
                "Use the Hospitals tab to search district-wise hospitals. The list includes both government and private hospitals generated for districts across India."
            }
            q.contains("pregnan") || q.contains("maternity") || q.contains("mother") -> {
                "You can check maternity-focused schemes like Janani Suraksha Yojana and related state schemes. These usually require ANC records, identity proof, and bank account details."
            }
            q.contains("senior") || q.contains("elderly") -> {
                "Senior citizens can explore PM-JAY eligibility, state health assurance schemes, and CGHS/ESI-linked facilities where applicable."
            }
            q.contains("insurance") || q.contains("coverage") -> {
                "Coverage depends on the scheme. PM-JAY provides cashless hospitalization up to Rs. 5 lakh; state schemes may provide additional package coverage."
            }
            q.contains("hello") || q.contains("hi") || q.contains("namaste") -> {
                "Namaste! Ask me about eligibility, documents, hospitals, or government health schemes and I will guide you."
            }
            else -> {
                "I can help with scheme eligibility, required documents, district hospitals, and policy updates. Please ask a specific question like 'How to apply for PM-JAY?'"
            }
        }
    }
}
