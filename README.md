# Software Development Design Document

## 1. Introduction

**Project Name**: Popular People Browser  
**APK Name**: FameStream  

### Objective
To display a list of popular people (actors, directors, etc.) with infinite scrolling and search functionality, along with detailed views of personal information and related images.

### Scope
The app fetches data from The Movie Database (TMDb) API and uses:
- Jetpack Compose for UI
- Hilt for dependency injection
- Paging for infinite scrolling

---

## 2. System Overview

### Technologies Used
- **Programming Language**: Kotlin  
- **UI Framework**: Jetpack Compose  
- **Architecture**: MVVM (Model-View-ViewModel)  
- **Networking**: Retrofit  
- **Dependency Injection**: Hilt  
- **Pagination**: Paging 3 library  
- **Navigation**: Jetpack Navigation Component  
- **Image Loading**: Coil  

### Key Features
- Infinite scrolling for popular people and search results.  
- Detailed view of selected individuals, displaying personal info and grid images.

---

## 3. System Architecture

### Layered Architecture
1. **UI Layer**:
   - Screens (e.g., `PopularPersonScreen.kt`, `DetailScreen.kt`)
   - Composables (e.g., `PersonItem.kt`, `Loader.kt`, `DetailScreenItem.kt`)  
   
2. **ViewModel Layer**:
   - Handles UI state and logic (e.g., `PopularListViewModel.kt`, `DetailPageViewModel.kt`)
   
3. **Repository Layer**:
   - Abstracts data sources (e.g., `MyRepository.kt`)  

4. **Network Layer**:
   - API interactions using Retrofit (e.g., `ApiService.kt`)

5. **Paging Layer**:
   - Provides data for infinite scrolling (e.g., `PagingSource.kt`)  

### Navigation Flow
Defined in `AppNavigation.kt`, managing transitions between the list and detail screens.

---

## 4. Package Structure

- **hilt**: For application-level dependency injection (e.g., `MyApplication`, `NetworkModule`).
- **model**: Data classes representing API responses (e.g., `PersonResponse.kt`).
- **navHost**: Handles navigation between screens (e.g., `AppNavigation.kt`).
- **networks**: Contains network services (e.g., `ApiService`, `MyRepository`).
- **paging**: Includes custom `PagingSource.kt` for infinite scrolling.
- **screens**: UI screens and reusable components.
- **viewModel**: Manages UI logic (e.g., `PopularListViewModel.kt`, `DetailPageViewModel.kt`).

---

## 5. Workflow

### Popular People List
1. `PopularPersonScreen` initializes the `PopularListViewModel`.
2. The ViewModel calls the repository to fetch data using `PagingSource`.
3. Data is displayed in a `LazyColumn`.
4. Scrolling triggers pagination requests.

### Search Functionality
1. Text input updates the query parameter in the ViewModel.
2. Repository fetches filtered data using `PagingSource`.
3. Data is displayed with pagination.

### Detail View
1. On clicking a person, their `Person` object is passed via navigation arguments.
2. `DetailScreen` fetches and displays the relevant grid data.

---

## 6. Design Decisions

1. **MVVM Architecture**: Chosen to separate concerns and improve testability.
2. **Paging 3 Library**: For efficient, memory-optimized pagination.
3. **Hilt for Dependency Injection**: Simplifies object creation and lifecycle management.
4. **Jetpack Compose**: Enables declarative UI creation for better maintainability.

---

## 7. Error Handling

1. **API Errors**: Handled in the Repository and surfaced through ViewModel states.
2. **Empty States**: Displays "No data found" for empty responses.
3. **Default Image**: When `AsyncImage` fails to load a URL, a "Not Available" default image is displayed.

---
