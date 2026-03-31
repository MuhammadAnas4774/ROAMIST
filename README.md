# 🗺️ Roamist — Pakistan Travel Companion

> **Exploring the Beauty of Pakistan** | Android Native | Modular Architecture | Dark Theme

**Roamist** is a high-performance native Android application built to showcase the breathtaking travel destinations across Pakistan. From the majestic peaks of Gilgit-Baltistan to the cultural heart of Punjab and the coastal beauty of Sindh, Roamist puts the best of Pakistan in the palm of your hand.

[![Project Status: Completed](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)](https://github.com/UsMaNBAjWaa/ROAMIST)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-orange?style=for-the-badge&logo=kotlin)](https://kotlinlang.org/)
[![Material UI](https://img.shields.io/badge/Design-Material_3-7C4DFF?style=for-the-badge&logo=materialdesign)](https://m3.material.io/)

---

## 🎯 Academic Compliance (Functional Requirements)

This project strictly adheres to academic implementation constraints (F1–F5) for modular Android development:

| ID | Requirement | Implementation Detail |
|----|-------------|-------------------|
| **F1** | **Intent Navigation** | Seamless transition from **LoginScreen** to **Dashboard** using explicit Intents with `EXTRA_USERNAME` and `SESSION_TOKEN` data passing. |
| **F2** | **Bundle Transfer** | Custom `Destination` objects implement `Parcelable` for efficient transfer between Fragments via Bundle arguments. |
| **F3** | **RecyclerView** | High-performance dynamic list displaying 12 real Pakistani destinations with custom `ViewHolder` and row layouts. |
| **F4** | **Fragment Transactions** | Robust tab-based navigation (Explore, Trips, Saved, Profile) and detailed view mapping using `supportFragmentManager` without Activity restarts. |
| **F5** | **Search / Filter** | Real-time, case-insensitive search filtering across destination names, regions, and categories using the `Filterable` interface. |

---

## 🏗️ Technical Architecture

### **Core Design Principles**
*   **Modular UI:** Pure Fragment-based content layer hosted within a container `DashboardActivity`.
*   **Data Integrity:** Zero use of `SharedPreferences` or global static variables for inter-screen communication; all data flows via **Intent Extras** and **Bundles**.
*   **Clean Organization:** Structured packaging into `.activities`, `.fragments`, `.adapters`, and `.models`.

### **Tech Stack**
*   **Language:** Kotlin
*   **Min SDK:** API 21 (Android 5.0+)
*   **Layout Engine:** XML Views + ConstraintLayout
*   **Theme:** Custom Roamist Dark Mode (Orange #FF6D00 & Graphite #1A1A1A)

---

## 🏔️ Featured Destinations
Explore 12 curated locations including:
*   🏔️ **Hunza Valley** (Gilgit-Baltistan)
*   🕌 **Lahore Old City** (Punjab)
*   🏖️ **Karachi Beach** (Sindh)
*   🏕️ **Skardu** (Gilgit-Baltistan)
*   🌲 **Nathia Gali** (KPK)
*   🦁 **Deosai Plains** (Wildlife)

---

## 📂 Project Structure

```text
com.example.roamist
├── activities/       # Navigation & Session Coordination
├── fragments/        # Core UI Hubs (List, Detail, Profile)
├── adapters/         # RecyclerView Binding & Filtering Logic
├── models/           # Parcelable Data Entities
└── res/              # Rich Vector Assets & Layouts
```

---

## 📄 Documentation
A full logic flow map documenting every class and method for requirement traceability is available in:
👉 **[logic_map.pdf](./logic_map.pdf)**

---

## 👥 Contributors
*   **Muhammad Anas** (MuhammadAnas4774) — Lead Android Developer
*   **Usman Alamgir** (UsMaNBAjWaa) — Lead Android Developer

---

**Built with ❤️ for Pakistan.**
