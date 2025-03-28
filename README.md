# AnimeDiaries - Android App

AnimeDiaries is an anime discovery application built using Jetpack Compose, MVVM architecture, and the Jikan API. This app allows users to explore popular anime, view detailed information, and watch YouTube trailers within the app.

## Features
- Browse top/popular anime with pagination
- View detailed anime information
- Watch YouTube trailers in-app
- Modern Material Design UI
- Clean architecture with MVVM pattern

## Tech Stack
- **UI:** Jetpack Compose
- **Architecture:** MVVM
- **Dependency Injection:** Dagger-Hilt
- **Networking:** Retrofit
- **Pagination:** Jetpack Paging
- **YouTube Player:** android-youtube-player
- **API:** Jikan API

## Implementation Details

### Key Components
#### Navigation
- Single-Activity architecture
- Navigation Compose with NavGraph
- Safe args for navigation

#### Pagination
- Jetpack Paging 3
- Load states handling (loading, error, empty)
- Infinite scrolling

#### YouTube Player
- Embedded YouTube player
- Lifecycle-aware playback
- URL parsing for video IDs

#### Dependency Injection Setup
- Hilt for dependency injection
- Separate modules for network, repository, etc.

## API Integration
AnimeDiaries utilizes the Jikan API for fetching anime-related data. The following endpoints are used:

- **Top anime list:** `v4/top/anime`
- **Anime details:** `v4/anime/{id}`

This app follows best practices in Android development, ensuring a seamless and optimized user experience.

