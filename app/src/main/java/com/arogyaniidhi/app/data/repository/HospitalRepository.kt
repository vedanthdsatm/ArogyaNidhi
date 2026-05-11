package com.arogyaniidhi.app.data.repository

import android.content.Context
import com.arogyaniidhi.app.data.model.Hospital
import com.google.gson.Gson

object HospitalRepository {

    private val coreHospitals = listOf(
        Hospital(
            id = "h1",
            name = "AIIMS New Delhi",
            address = "Sri Aurobindo Marg, Ansari Nagar",
            district = "New Delhi",
            state = "Delhi",
            phone = "011-26588500",
            acceptedSchemes = listOf("pmjay", "cghs", "rsby"),
            specialties = listOf("Cardiology", "Oncology", "Neurology", "Orthopaedics"),
            isGovernment = true
        ),
        Hospital(
            id = "h2",
            name = "Safdarjung Hospital",
            address = "Ansari Nagar West, New Delhi",
            district = "New Delhi",
            state = "Delhi",
            phone = "011-26707444",
            acceptedSchemes = listOf("pmjay", "cghs", "rsby", "pmsby"),
            specialties = listOf("General Medicine", "Surgery", "Gynaecology", "Paediatrics"),
            isGovernment = true
        ),
        Hospital(
            id = "h3",
            name = "Government Medical College",
            address = "Medical College Road",
            district = "Nagpur",
            state = "Maharashtra",
            phone = "0712-2740560",
            acceptedSchemes = listOf("pmjay", "rsby"),
            specialties = listOf("General Medicine", "ENT", "Ophthalmology"),
            isGovernment = true
        ),
        Hospital(
            id = "h4",
            name = "Victoria Hospital",
            address = "K.R. Road, Chamrajpet",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-26706928",
            acceptedSchemes = listOf("pmjay", "rsby", "schis"),
            specialties = listOf("General Surgery", "Orthopaedics", "Neurology"),
            isGovernment = true
        ),
        Hospital(
            id = "h5",
            name = "Indira Gandhi Government Hospital",
            address = "IG Hospital Road",
            district = "Chennai",
            state = "Tamil Nadu",
            phone = "044-25356000",
            acceptedSchemes = listOf("pmjay", "cghs"),
            specialties = listOf("Cardiology", "Nephrology", "Haematology"),
            isGovernment = true
        ),
        Hospital(
            id = "h6",
            name = "Apollo Hospitals",
            address = "21 Greams Lane, Off Greams Road",
            district = "Chennai",
            state = "Tamil Nadu",
            phone = "044-28290200",
            acceptedSchemes = listOf("pmjay", "cghs"),
            specialties = listOf("Cardiology", "Oncology", "Transplant", "Neurology"),
            isGovernment = false
        ),
        Hospital(
            id = "h7",
            name = "Manipal Hospital",
            address = "98 HAL Airport Road",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-25024444",
            acceptedSchemes = listOf("pmjay", "cghs", "schis"),
            specialties = listOf("Cardiology", "Robotic Surgery", "Bone Marrow Transplant"),
            isGovernment = false
        ),
        Hospital(
            id = "h8",
            name = "District Hospital Varanasi",
            address = "Kabirchaura, Varanasi",
            district = "Varanasi",
            state = "Uttar Pradesh",
            phone = "0542-2209421",
            acceptedSchemes = listOf("pmjay", "rsby", "jsy"),
            specialties = listOf("General Medicine", "Maternity", "Paediatrics"),
            isGovernment = true
        ),
        Hospital(
            id = "h9",
            name = "NIMHANS",
            address = "Hosur Road, Lakkasandra",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-46110007",
            acceptedSchemes = listOf("pmjay", "cghs"),
            specialties = listOf("Psychiatry", "Neurology", "Neurosurgery"),
            isGovernment = true
        ),
        Hospital(
            id = "h10",
            name = "Rajiv Gandhi Government Hospital",
            address = "Park Town",
            district = "Chennai",
            state = "Tamil Nadu",
            phone = "044-25305000",
            acceptedSchemes = listOf("pmjay", "rsby", "schis", "dds"),
            specialties = listOf("General Medicine", "Emergency", "Burns", "Plastic Surgery"),
            isGovernment = true
        )
    )

    private var hospitals: List<Hospital> = coreHospitals

    private data class StateDistrictPayload(
        val states: List<StateDistrictItem>
    )

    private data class StateDistrictItem(
        val state: String,
        val districts: List<String>
    )

    fun ensureLoaded(context: Context) {
        if (hospitals.size > coreHospitals.size) return
        val districtHospitals = loadDistrictHospitals(context)
        hospitals = coreHospitals + districtHospitals
    }

    private fun loadDistrictHospitals(context: Context): List<Hospital> {
        return try {
            val json = context.assets.open("india_states_districts.json")
                .bufferedReader()
                .use { it.readText() }
            val payload = Gson().fromJson(json, StateDistrictPayload::class.java)
            payload.states.flatMapIndexed { stateIndex, stateItem ->
                stateItem.districts.flatMapIndexed { districtIndex, district ->
                    val safeDistrict = district.trim()
                    val idBase = "${stateIndex}_${districtIndex}"
                    listOf(
                        Hospital(
                            id = "gov_$idBase",
                            name = "$safeDistrict District Government Hospital",
                            address = "Main Civil Hospital Road, $safeDistrict",
                            district = safeDistrict,
                            state = stateItem.state,
                            phone = "1800-11-0101",
                            acceptedSchemes = listOf("pmjay", "rsby", "jsy"),
                            specialties = listOf("General Medicine", "Emergency", "Maternity"),
                            isGovernment = true
                        ),
                        Hospital(
                            id = "pvt_$idBase",
                            name = "$safeDistrict Care Multispeciality",
                            address = "Health Circle, $safeDistrict",
                            district = safeDistrict,
                            state = stateItem.state,
                            phone = "1800-11-0202",
                            acceptedSchemes = listOf("pmjay", "cghs"),
                            specialties = listOf("Cardiology", "Orthopaedics", "Diagnostics"),
                            isGovernment = false
                        )
                    )
                }
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

    fun getAllDistricts(): List<String> {
        return hospitals.map { it.district }.distinct().sorted()
    }

    fun getAllStates(): List<String> {
        return hospitals.map { it.state }.distinct().sorted()
    }

    fun searchByDistrict(district: String): List<Hospital> {
        return hospitals.filter {
            it.district.contains(district, ignoreCase = true)
        }
    }

    fun filterByScheme(schemeId: String): List<Hospital> {
        return hospitals.filter { schemeId in it.acceptedSchemes }
    }

    fun filterBySchemes(schemeIds: List<String>): List<Hospital> {
        return hospitals.filter { hospital ->
            schemeIds.any { it in hospital.acceptedSchemes }
        }
    }

    fun searchHospitals(query: String, district: String = "", schemeId: String = ""): List<Hospital> {
        return hospitals.filter { hospital ->
            val matchesQuery = query.isEmpty() ||
                    hospital.name.contains(query, ignoreCase = true) ||
                    hospital.district.contains(query, ignoreCase = true) ||
                    hospital.state.contains(query, ignoreCase = true)
            val matchesDistrict = district.isEmpty() ||
                    hospital.district.contains(district, ignoreCase = true)
            val matchesScheme = schemeId.isEmpty() ||
                    schemeId in hospital.acceptedSchemes
            matchesQuery && matchesDistrict && matchesScheme
        }
    }
}
