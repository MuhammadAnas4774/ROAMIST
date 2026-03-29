# 📱 Roamist — App Documentation

> **Pakistan Travel App** | Android Native | Dark Theme | PKR Currency

---

## 1. Introduction

### What is Roamist?

**Roamist** is a native Android travel application designed exclusively for exploring **Pakistan** — one of the world's most breathtaking, diverse, and underrated travel destinations. Whether you are planning a trek through the majestic peaks of Gilgit-Baltistan, discovering the ancient heritage of Mohenjo-daro in Sindh, or wandering through the vibrant streets of Lahore, Roamist puts all of Pakistan's travel experiences in the palm of your hand.

The app is built with a **dark, modern UI** using Pakistan's signature green (`#01411C`) as its brand color, complemented by an energetic **accent orange** (`#FF6D00`) for calls-to-action and highlights. The design philosophy prioritizes clarity, speed, and cultural authenticity — featuring **Urdu region names** alongside English, **PKR pricing**, and Pakistan-specific content throughout.

---

### Core Purpose

Roamist serves as an **all-in-one travel companion** for Pakistani travellers and tourists visiting Pakistan:

- 🗺️ **Discover** 124+ curated destinations across 6 major regions
- 🏨 **Book** trips with hotel, transport, food, guide, and trekking packages
- 📋 **Track** booking history and manage upcoming trips
- 🧭 **Navigate** via an upcoming interactive map with real-time route support
- 🔔 **Stay informed** with trip alerts, weather warnings, and seasonal deals
- 👤 **Personalise** travel style, budget, language, and favourite regions

---

### Key App Features

| Feature | Description |
|---------|-------------|
| 🏔️ **Home Dashboard** | Hero banner, category chips, trending destinations, nearby stays |
| 🃏 **Destination Cards** | Destination name (English + Urdu), price in PKR, guide avatar, action buttons |
| 🗂️ **Bottom Navigation** | Explore · My Trips · Saved · Profile — 4 tabs with Pakistan-green active state |
| 📊 **Booking History** | Full booking records with status badges (Completed / Upcoming) |
| 🌍 **Region Explorer** | Grid of all 6 regions: Northern Areas, Punjab, Sindh, KPK, Balochistan, GB |
| 🗺️ **Map Explorer** | Coming-soon screen with feature teasers for live navigation & offline maps |
| ⚙️ **App Settings** | Notifications, GPS, dark mode, language, currency, privacy & 2FA |
| 👤 **Profile Screen** | Avatar, stats (trips/cities/reviews), personal info, travel preferences |
| 🔐 **Auth Screens** | Sign In and Create Account flows with card-based design |
| 🔔 **Trip Alerts Hub** | Bell icon with red notification badge showing unread count |

---

### Pakistan-Specific Content

- **Destinations covered:** Hunza, Skardu, Swat, Murree, Naran, Kaghan, Lahore Fort, Badshahi Mosque, Mohenjo-daro, Fairy Meadows, Attabad Lake, Baltit Fort
- **Regions (with Urdu):** پنجاب ، سندھ ، خیبر پختونخوا ، بلوچستان ، گلگت بلتستان
- **Currency:** PKR — Pakistani Rupee (Rs. X,XXX format)
- **Languages:** English (primary) + Urdu (region labels, dual-language UI)
- **Seasons:** Best time callouts (e.g., Apr – Oct for northern mountains, Mar – May for cherry blossoms)
- **Transport:** Daewoo bus, Pakistan Railways references
- **Services:** Hotel, Transport, Local Guide, Food, Trekking, Flights, Weather, Nearby

---

### Technology Stack

| Layer | Technology |
|-------|-----------|
| Platform | Android (Native) |
| Language | Kotlin |
| Layout Engine | Android XML Views |
| UI Library | Material Design Components (MDC) |
| Map SDK | Google Maps (SupportMapFragment — planned) |
| Min SDK | Android 5.0+ (API 21) |
| Theme | Dark Mode (custom Roamist theme) |

---

## 2. Layout Reference Table

The table below lists every layout file in the app, the **screen / component** it represents, its **root layout type**, and the **key layout elements** it uses.

| # | Layout File | Screen / Component | Root Layout | Key Layouts & Components Used |
|---|-------------|-------------------|-------------|-------------------------------|
| 1 | `activity_main.xml` | **Home Dashboard** | `CoordinatorLayout` | `AppBarLayout` + `Toolbar`, `NestedScrollView`, `LinearLayout` (hero banner with `header_gradient_bg`), `CardView` (stats row, `weightSum="3"`), filter chip `TextView` rows, `include` for destination cards & nearby section, `FloatingActionButton` (Plan Trip), `include` for bottom nav |
| 2 | `activity_login.xml` | **Sign In Screen** | `ScrollView` | `LinearLayout` (vertical, `fillViewport`), branding `TextView` headers, `CardView` (20dp radius, 16dp elevation) containing `LinearLayout` with `EditText` fields (`input_field_background`), `Button` (Sign In + Google), divider `View` strip with OR label |
| 3 | `activity_signup.xml` | **Create Account Screen** | `ScrollView` | `LinearLayout` (vertical, `fillViewport`), branding `TextView` headers, `CardView` (20dp radius) containing `LinearLayout` with 5 `EditText` fields (Full Name, Email, Phone, Password, Confirm Password), Terms note `TextView`, `Button` (Create Account + Google), navigation link row |
| 4 | `activity_profile.xml` | **My Profile Screen** | `LinearLayout` (vertical) | `AppBarLayout` + `Toolbar` (title "My Profile"), hero banner `LinearLayout` (`header_gradient_bg`) with `FrameLayout` avatar circle + `AppCompatImageView`, stat cards in horizontal `LinearLayout` (`weightSum="3"`), `NestedScrollView` with `CardView` sections: Personal Info, Travel Preferences, Account Settings (with `SwitchCompat` for GPS), `Button` Sign Out |
| 5 | `activity_settings.xml` | **Trip Info / Settings Detail** | `CoordinatorLayout` | `AppBarLayout` + `Toolbar` (title "Trip Info", location nav icon), `NestedScrollView` → `LinearLayout`, `CardView` hero (with `FrameLayout` gradient header at 140dp + quick stats row `weightSum="4"`), service icon grid (`LinearLayout` `weightSum="4"` × 2 rows), weather card (`LinearLayout` horizontal with `ImageView`), trip highlights `CardView` with bullet-dot items |
| 6 | `activity_booking_history.xml` | **Booking History** | `CoordinatorLayout` | `AppBarLayout` + `Toolbar` (title "Booking History"), `NestedScrollView` → `LinearLayout`, summary banner `CardView` (`weightSum="3"`: Total Trips / PKR Spent / Upcoming), section label, multiple booking `CardView` blocks each with: destination + status badge (`badge_completed` / `badge_upcoming`), detail row `LinearLayout` (`weightSum="3"`: Date / Duration / Amount), guide row + action `Button` pair (Review + Re-Book, or Cancel + Details) |
| 7 | `activity_app_settings.xml` | **App Settings** | `CoordinatorLayout` | `AppBarLayout` + `Toolbar` (title "App Settings"), `NestedScrollView` → `LinearLayout`, section headers (orange divider + bold `TextView`), `CardView` per section — each containing `LinearLayout` → `RelativeLayout` rows (56dp height) with label + `Switch` (Trip Alerts, Deals, Weather Warnings, GPS, Offline Maps, 2FA) or label + chevron `ImageView` (Theme, Language, Currency, Travel Style, Budget, Region, Password, Privacy Policy) |
| 8 | `activity_map_view.xml` | **Map Explorer** | `CoordinatorLayout` | `AppBarLayout` + `Toolbar` (title "Map Explorer"), `NestedScrollView` → `LinearLayout`, decorative `FrameLayout` (220dp) with faint grid `View` lines + orange `map_dot` views, "COMING SOON" label + accent underline `View`, description `TextView`, 4 feature teaser `LinearLayout` cards (`feature_card_border` background: Live Routes, Nearby POI, Weather Overlay, Offline Maps), "Notify Me" `CardView` with `Button` + link `TextView` |
| 9 | `item_destination_card.xml` | **Destination Card (RecyclerView item)** | `CardView` (12dp radius) | `ConstraintLayout` with vertical `Guideline` at 20%, `Barrier`, destination image area, `TextView` (name, region, price in PKR, Urdu label), guide avatar `ImageView` (`ic_guide_placeholder`), action chain: View Details \| Add to Wishlist \| Share (`spread` chain) |
| 10 | `layout_bottom_nav.xml` | **Bottom Navigation Bar** | `LinearLayout` (horizontal) | `weightSum="4"`, 4 nav items each `layout_weight="1"`: Explore (compass), My Trips (calendar), Saved (heart), Profile (person); `nav_item_selector` for Pakistan-green active state |
| 11 | `layout_nearby.xml` | **Nearby Attractions Carousel** | `HorizontalScrollView` | Inner `LinearLayout` (horizontal, no scrollbars), attraction cards 120dp wide with 8dp margins; items: Food Street Lahore (`ic_food`), Mohenjo-daro Ruins (`ic_monument`), Fairy Meadows Trek (`ic_mountain`) |
| 12 | `fragment_explore_grid.xml` | **Pakistan Regions Grid** | `ScrollView` → `GridLayout` | `columnCount="2"`, featured "Northern Areas" card `layout_columnSpan="2"` with `hunza_gradient`, remaining region cards: Punjab (Lahore), Sindh (Karachi), KPK (Peshawar), Balochistan (Quetta), Gilgit-Baltistan (Hunza); each with icon, English name, Urdu name, trip count |
| 13 | `view_notification_badge.xml` | **Trip Alerts Bell Badge** | `FrameLayout` | Bell vector icon 24dp; red oval `TextView` badge (`badge_background` #F44336) at `layout_gravity="top|end"` showing unread count (e.g. "3"); ripple foreground for click feedback |

---

## 3. Drawable & Resource Assets Used

### Color Palette

| Token | Hex | Usage |
|-------|-----|-------|
| `pakistan_green` | `#01411C` | Brand primary, active nav |
| `accent_orange` | `#FF6D00` | CTAs, highlights, FAB |
| `pakistan_white` | `#FFFFFF` | Text, icons |
| `mountain_blue` | `#1E3A5F` | Header gradient end |
| `desert_sand` | `#C2B280` | Subtle accents |
| `notification_badge_red` | `#F44336` | Bell badge background |
| `status_completed` | `#4CAF50` | Booking completed badge |
| `status_upcoming` | `#FF9800` | Booking upcoming badge |
| `primary_dark` | (dark bg) | Screen background |
| `surface_dark` | (card surface) | Card backgrounds |

### Key Drawables

| Drawable | Type | Used In |
|----------|------|---------|
| `header_gradient_bg.xml` | Gradient shape | Home hero banner, Profile hero |
| `hunza_header_gradient.xml` | Gradient shape | Trip Info hero, Region grid featured card |
| `input_field_background.xml` | Shape drawable | All `EditText` fields (Login, Signup) |
| `btn_ripple.xml` | Ripple drawable | Primary action buttons |
| `badge_completed.xml` | Shape (green oval) | Booking History status badge |
| `badge_upcoming.xml` | Shape (orange oval) | Booking History status badge |
| `badge_background.xml` | Shape (red oval) | Notification bell count badge |
| `nav_item_selector.xml` | State selector | Bottom nav active state |
| `map_dot.xml` | Shape (circle) | Map Explorer decorative dots |
| `orange_divider.xml` | Shape | Section accent bar (4dp wide) |
| `section_card_background.xml` | Shape | Google sign-in buttons |
| `stat_card_background.xml` | Shape | Service icon containers |
| `ic_mountain.xml` | Vector | Northern areas, Trekking service |
| `ic_mosque.xml` | Vector | Religious sites |
| `ic_food.xml` | Vector | Food service, Nearby carousel |
| `ic_transport.xml` | Vector | Transport service |
| `ic_weather.xml` | Vector | Weather card |
| `ic_monument.xml` | Vector | Hotel service icon, Nearby carousel |
| `ic_bell.xml` | Vector | Notification badge, Toolbar |
| `ic_airplane.xml` | Vector | Plan Trip FAB |
| `ic_location.xml` | Vector | GPS settings, Map toolbar |
| `ic_person.xml` | Vector | Profile avatar placeholder |

---

## 4. Screen Flow Diagram

```
┌─────────────┐     ┌─────────────┐
│  Login      │────▶│   Sign Up   │
│ (ScrollView)│     │ (ScrollView)│
└──────┬──────┘     └─────────────┘
       │ Auth Success
       ▼
┌─────────────────────────────────────────┐
│         Home Dashboard (Main)           │
│  CoordinatorLayout + NestedScrollView   │
└──────┬────────────────┬─────────────────┘
       │ Bottom Nav     │ Bottom Nav
       ▼                ▼
┌──────────────┐  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐
│   Explore    │  │  My Trips    │  │    Saved     │  │   Profile    │
│  (Fragment   │  │  (Booking    │  │  (Wishlist)  │  │ (LinearLayout│
│   GridLayout)│  │   History)   │  │              │  │    + NSV)    │
└──────────────┘  └──────┬───────┘  └──────────────┘  └──────────────┘
                         │ Tap booking
                         ▼
                  ┌──────────────┐
                  │  Trip Info   │
                  │(activity_    │
                  │ settings.xml)│
                  └──────────────┘
```

> **Note:** `activity_settings.xml` in the codebase is repurposed as the **Trip Info** detail screen (not the user-facing "settings" page). The actual app settings screen is `activity_app_settings.xml`.

---

## 5. File Index

| Layout File | Screen / Purpose |
|-------------|-----------------|
| `res/layout/activity_main.xml` | Home Dashboard |
| `res/layout/activity_login.xml` | Sign In Screen |
| `res/layout/activity_signup.xml` | Create Account Screen |
| `res/layout/activity_profile.xml` | My Profile Screen |
| `res/layout/activity_settings.xml` | Trip Info Detail Screen |
| `res/layout/activity_booking_history.xml` | Booking History Screen |
| `res/layout/activity_app_settings.xml` | App Settings Screen |
| `res/layout/activity_map_view.xml` | Map Explorer Screen |
| `res/layout/item_destination_card.xml` | Destination Detail Card (list item) |
| `res/layout/layout_bottom_nav.xml` | Bottom Navigation Bar |
| `res/layout/layout_nearby.xml` | Nearby Attractions Carousel |
| `res/layout/fragment_explore_grid.xml` | Pakistan Regions Grid Fragment |
| `res/layout/view_notification_badge.xml` | Trip Alerts Bell + Badge |
| `res/values/colors.xml` | Color tokens |
| `res/values/strings.xml` | String resources |
| `res/values/dimens.xml` | Dimension tokens |
| `res/values/styles.xml` | Text appearance styles |
| `res/values/themes.xml` | App theme (Roamist dark) |
| `res/drawable/*.xml` | Vectors, gradients, shapes, selectors |

---

*Documentation generated: March 2026 | Roamist v1.0 | Pakistan Travel App*
