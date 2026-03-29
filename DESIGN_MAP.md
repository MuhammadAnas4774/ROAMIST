# Roamist — Design Map (Pakistan Travel App)

Design map for all main screens and components. All layouts are Android XML (no HTML).

---

## Requirement Matrix

| ID | Screen Filename | Function | Key Components |
|----|-----------------|----------|-----------------|
| L1 | `activity_main.xml` | Home Dashboard | CoordinatorLayout, CollapsingToolbar (Northern Pakistan header), NestedScrollView, FAB "Plan Trip" |
| L2 | `item_destination_card.xml` | Destination Detail Card | ConstraintLayout, 20% Guideline, Barrier (Urdu/English), Chain (View Details \| Add to Wishlist \| Share), guide avatar, 12dp card |
| L3 | `layout_bottom_nav.xml` | Bottom Navigation | LinearLayout weightSum=4, 4 tabs: Explore, My Trips, Saved, Profile; active state Pakistan green |
| L4 | `activity_settings.xml` | Settings Row | RelativeLayout, location icon, "Enable GPS Tracking", Switch layout_alignParentEnd, divider shape |
| L5 | `view_notification_badge.xml` | Trip Alerts Hub | FrameLayout, bell icon 24dp, red badge (#F44336), ripple |
| L6 | `fragment_explore_grid.xml` | Pakistan Regions Grid | GridLayout columnCount=2, Northern Areas (span 2), Punjab/Sindh/KPK/Balochistan/Gilgit-Baltistan, trip counts |
| L7 | `activity_booking_history.xml` | Booking History Table | TableLayout stretchColumns 1,3, headers Date \| Destination \| Status \| Amount (PKR), status badges |
| L8 | `layout_nearby_carousel.xml` | Nearby Attractions | HorizontalScrollView, cards 120dp: Food Street Lahore, Mohenjo-daro Ruins, Fairy Meadows Trek |
| L9 | `dialog_filter_trips.xml` | Trip Filter | RadioGroup: Price Low–High, Top Rated, Nearest, Best Season; custom radio drawable; Apply ripple |
| L10 | `layout_interest_tags.xml` | Travel Preferences | ConstraintLayout + Flow, chips: Adventure, Culture, Food, History, Nature, Photography |
| — | `activity_map_view.xml` | Map Integration | FragmentContainerView (SupportMapFragment), overlay search + filter chips, bottom sheet nearby hotels, marker info: `layout_marker_info.xml` |

---

## Screen Specifications

### L1 — Home Dashboard (`activity_main.xml`)
- **Root:** `CoordinatorLayout`
- **App bar:** `AppBarLayout` (256dp) + `CollapsingToolbarLayout`; title "Roamist" (expanded → collapsed).
- **Header:** `hunza_header_gradient.xml` (blue sky → mountain blue); overlay gradient; parallax collapse.
- **Content:** `NestedScrollView` with "Trending in Pakistan" and repeated `item_destination_card`.
- **FAB:** "Plan Trip", Pakistan green, `ic_airplane.xml`.

### L2 — Destination Card (`item_destination_card.xml`)
- **Root:** `CardView` 12dp radius, 8dp elevation.
- **Layout:** `ConstraintLayout` with vertical Guideline at 20% for image area.
- **Barrier:** Keeps description from overlapping price/Urdu (`dest_price`, `dest_price_urdu`).
- **Chain:** View Details, Add to Wishlist, Share (spread).
- **Assets:** Guide avatar `ic_guide_placeholder.xml`, card background.

### L3 — Bottom Nav (`layout_bottom_nav.xml`)
- **Root:** `LinearLayout` horizontal, `weightSum="4"`, four items `layout_weight="1"`.
- **Tabs:** Explore (compass), My Trips (calendar), Saved (heart), Profile (person).
- **Active state:** `nav_item_selector.xml` (Pakistan green when selected).

### L4 — Settings Row (`activity_settings.xml`)
- **Root:** `RelativeLayout`.
- **Icon:** `ic_location.xml`, `layout_alignParentStart`, `layout_centerVertical`.
- **Text:** "Enable GPS Tracking", `layout_toRightOf` icon.
- **Switch:** `layout_alignParentEnd`, `layout_centerVertical`.
- **Divider:** `divider_line.xml` below row.

### L5 — Notification Badge (`view_notification_badge.xml`)
- **Root:** `FrameLayout`, `wrap_content`, ripple foreground.
- **Layers:** Bell vector 24dp; red oval badge `layout_gravity="top|end"` with count (e.g. "3"), `badge_background.xml` (#F44336).

### L6 — Regions Grid (`fragment_explore_grid.xml`)
- **Root:** `ScrollView` → `GridLayout` `columnCount="2"`.
- **Featured:** "Northern Areas" card `layout_columnSpan="2"`, `hunza_gradient`, trip count.
- **Cards:** Punjab (Lahore), Sindh (Karachi), KPK (Peshawar), Balochistan (Quetta), Gilgit-Baltistan (Hunza); icon + English + Urdu + trip count.

### L7 — Booking History (`activity_booking_history.xml`)
- **Table:** `TableLayout` `stretchColumns="1,3"`.
- **Headers:** Date | Destination | Status | Amount (PKR).
- **Rows:** e.g. 12 Mar 2024 \| Hunza Valley \| Completed \| Rs. 45,000; 05 Feb 2024 \| Skardu \| Upcoming \| Rs. 38,500.
- **Badges:** `badge_completed.xml` (green), `badge_upcoming.xml` (orange).

### L8 — Nearby Carousel (`layout_nearby_carousel.xml`)
- **Root:** `HorizontalScrollView` (no scrollbars).
- **Cards:** 120dp width, 8dp margin; Food Street Lahore (`ic_food`), Mohenjo-daro Ruins (`ic_monument`), Fairy Meadows Trek (`ic_mountain`).

### L9 — Filter Dialog (`dialog_filter_trips.xml`)
- **RadioGroup:** Price: Low to High (default), Top Rated (4.5+), Nearest to Me, Best Season: Mar–May.
- **Drawable:** `radio_button_selector.xml` (Pakistan green when checked).
- **Button:** "Apply Filters", `btn_ripple.xml`.

### L10 — Interest Tags (`layout_interest_tags.xml`)
- **Root:** `ConstraintLayout`.
- **Title:** "Travel Preferences".
- **Flow:** `flow_wrapMode="chain"`, referenced IDs for 6 chips (Adventure, Culture, Food, History, Nature, Photography); `chip_background.xml`.

### Map Screen (`activity_map_view.xml`)
- **Map:** `FragmentContainerView` `SupportMapFragment`.
- **Overlay:** ConstraintLayout with search `EditText` and horizontal filter chips (Hotels, Restaurants, Attractions, Gas Stations).
- **Bottom sheet:** `CardView` with `layout_behavior="...BottomSheetBehavior"`, title "Nearby Stays", `RecyclerView` (item: `item_destination_card`).
- **Marker info:** Custom info window layout: `layout_marker_info.xml` (title, snippet, "View Details" link).

---

## Values & Assets

### Colors (`res/values/colors.xml`)
- `pakistan_green` #01411C  
- `pakistan_white` #FFFFFF  
- `accent_gold` #FFD700  
- `mountain_blue` #1E3A5F  
- `desert_sand` #C2B280  
- `notification_badge_red` #F44336  
- `status_completed` #4CAF50  
- `status_upcoming` #FF9800  

### Vector Drawables (XML)
- `ic_mountain.xml` — Northern areas  
- `ic_mosque.xml` — Religious sites  
- `ic_food.xml` — Food  
- `ic_transport.xml` — Bus/train  
- `ic_weather.xml` — Climate  
- `ic_monument.xml` — Mohenjo-daro / ruins  
- `ic_bell.xml` — Notifications  
- `ic_airplane.xml` — Plan trip FAB  
- `ic_location.xml` — GPS / location  
- `ic_guide_placeholder.xml` — Guide avatar  

### Shapes & Selectors
- `hunza_header_gradient.xml`, `hunza_overlay_gradient.xml` — Header  
- `card_background.xml`, `chip_background.xml` — Cards/chips  
- `badge_background.xml` (oval, red), `badge_completed.xml`, `badge_upcoming.xml`  
- `divider_line.xml`, `fab_background.xml`, `btn_ripple.xml`, `radio_button_selector.xml`  
- `nav_item_selector.xml` — Bottom nav active state  

### Dimens (`res/values/dimens.xml`)
- Spacing (xs/sm/md/lg/xl), card radius/elevation/padding, header height, bottom nav height, carousel card width/margin.

### Styles (`res/values/styles.xml`)
- `TextAppearance.Roamist.ExpandedTitle` / `CollapsedTitle` for toolbar.  
- Theme uses `colorPrimary` (pakistan_green), `colorSecondary` (accent_gold), status/nav bar.

---

## Pakistan-Specific Content

- **Destinations:** Hunza, Skardu, Swat, Murree, Naran, Kaghan, Lahore Fort, Badshahi Mosque, Mohenjo-daro.  
- **Currency:** PKR (Rs. X,XXX).  
- **Languages:** English primary; Urdu in regions (e.g. پنجاب، سندھ، خیبر پختونخوا، بلوچستان، گلگت بلتستان).  
- **Seasons:** "Best time to visit" (e.g. Mar–May for mountains).  
- **Transport:** Daewoo bus, Pakistan Railways (placeholders in copy/strings).

---

## File Index

| Path | Purpose |
|------|--------|
| `res/layout/activity_main.xml` | L1 Home |
| `res/layout/item_destination_card.xml` | L2 Card |
| `res/layout/layout_bottom_nav.xml` | L3 Nav |
| `res/layout/activity_settings.xml` | L4 Settings |
| `res/layout/view_notification_badge.xml` | L5 Badge |
| `res/layout/fragment_explore_grid.xml` | L6 Grid |
| `res/layout/activity_booking_history.xml` | L7 Table |
| `res/layout/layout_nearby_carousel.xml` | L8 Carousel |
| `res/layout/dialog_filter_trips.xml` | L9 Filter |
| `res/layout/layout_interest_tags.xml` | L10 Tags |
| `res/layout/activity_map_view.xml` | Map |
| `res/layout/layout_marker_info.xml` | Marker info |
| `res/values/colors.xml` | Colors |
| `res/values/strings.xml` | Strings |
| `res/values/dimens.xml` | Dimens |
| `res/values/styles.xml` | Styles |
| `res/values/themes.xml` | Theme (Roamist) |
| `res/drawable/*.xml` | Vectors, shapes, selectors |
