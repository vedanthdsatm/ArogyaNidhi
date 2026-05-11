# Arogya-Nidhi 🏥
**Android App – Health Scheme Eligibility Checker**
*Project Title: 61 | MindMatrix VTO Internship Program*

---

## 📌 About
Arogya-Nidhi helps rural families discover which government health schemes they are eligible for. Users answer a 5-step quiz about their family, and the app returns matching schemes, required documents, and nearby empanelled hospitals.

---

## ✨ Features
| Feature | Description |
|---------|-------------|
| **Eligibility Quiz** | 5-step stepper UI — income, occupation, BPL status, family size, location |
| **Scheme Results** | Lists all matching central/state government health schemes |
| **Document Checklist** | Interactive tick-off checklist of documents needed per scheme |
| **Hospital Finder** | Searchable list of empanelled hospitals, filterable by district |
| **Decision Tree Engine** | Pure Kotlin eligibility logic (if-else / decision tree in `EligibilityEngine.kt`) |
| **Offline First** | All data stored locally — no internet required |

---

## 🏛️ Schemes Covered
- Ayushman Bharat – PM-JAY (₹5 lakh cover)
- Central Government Health Scheme (CGHS)
- Pradhan Mantri Suraksha Bima Yojana
- Janani Suraksha Yojana (JSY)
- Rashtriya Swasthya Bima Yojana (RSBY)
- Senior Citizen Health Insurance Scheme
- ADIP (Disability Support)

---

## 🛠️ Tech Stack
- **Language:** Kotlin
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 34 (Android 14)
- **Architecture:** MVVM (ViewModel + LiveData)
- **Navigation:** Jetpack Navigation Component
- **UI:** Material Design 3 Components
- **Data:** In-memory repository + Kotlin data classes

---

## 🚀 How to Run in Android Studio

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 8 or higher
- Android SDK 34

### Steps
1. **Open** Android Studio
2. Click **"Open an existing project"**
3. Navigate to and select the `ArogyaNidhi` folder
4. Wait for Gradle sync to complete (first time may take a few minutes)
5. Connect a device or start an emulator (API 24+)
6. Click **▶ Run** or press `Shift+F10`

---

## 📁 Project Structure
```
app/src/main/
├── java/com/arogyaniidhi/app/
│   ├── data/
│   │   ├── model/
│   │   │   └── Models.kt          ← FamilyProfile, HealthScheme, Hospital
│   │   └── repository/
│   │       ├── EligibilityEngine.kt  ← Decision Tree Logic
│   │       └── HospitalRepository.kt ← Sample hospital data
│   └── ui/
│       ├── MainActivity.kt
│       ├── eligibility/
│       │   ├── EligibilityQuizFragment.kt  ← Stepper UI (5 steps)
│       │   └── EligibilityViewModel.kt
│       ├── result/
│       │   ├── ResultFragment.kt
│       │   └── SchemeResultAdapter.kt
│       ├── documents/
│       │   ├── DocumentGuideFragment.kt
│       │   └── DocumentChecklistAdapter.kt
│       └── hospitals/
│           ├── HospitalListFragment.kt
│           └── HospitalAdapter.kt
└── res/
    ├── layout/         ← All XML layouts
    ├── navigation/     ← nav_graph.xml
    ├── menu/           ← Bottom navigation menu
    └── values/         ← colors, strings, themes
```

---

## 📱 App Flow
```
Launch
  └─► Eligibility Quiz (5 Steps)
        ├── Step 1: Annual Income
        ├── Step 2: Occupation Type
        ├── Step 3: BPL / Ayushman Cards
        ├── Step 4: Family Details (size, disability, senior)
        └── Step 5: State/Location
              └─► Results Screen
                    ├── Eligible Scheme Cards
                    │     └─► Document Checklist (per scheme)
                    └─► Find Hospitals ──► Hospital List (searchable by district)
```

---

## 🔧 Extending the App
- **Add more schemes:** Edit `EligibilityEngine.kt` and add a new `HealthScheme` block
- **Add hospitals:** Add entries to the `hospitals` list in `HospitalRepository.kt`
- **Room DB:** Replace in-memory lists with Room entities for persistent storage
- **State-specific schemes:** Add state parameter checks in `EligibilityEngine`

---

## 📜 License
Internal project for MindMatrix VTO Internship Program.
# ArogyaNidhi-
