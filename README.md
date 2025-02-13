Software Development Design Document
1. Introduction
Project Name: Popular People Browser
APK Name: FameStream
Objective: To display a list of popular people (actors, directors, etc.) with infinite scrolling and search functionality, along with detailed views of personal information and related images.
Scope: The app fetches data from The Movie Database (TMDb) API and uses Jetpack Compose for UI, Hilt for dependency injection, and Paging for infinite scrolling.

2. System Overview
Technologies Used:
Programming Language: Kotlin
UI Framework: Jetpack Compose
Architecture: MVVM (Model-View-ViewModel)
Networking: Retrofit
Dependency Injection: Hilt
Pagination: Paging 3 library
Navigation: Jetpack Navigation Component
Coil: For loading and displaying images asynchronously.
Key Features:
Infinite scrolling for popular people and search results.
Detailed view of selected individuals, displaying personal info and grid images.




3. System Architecture
Layered Architecture:
UI Layer:


Screens (e.g., PopularPersonScreen.kt, DetailScreen.kt)
Composables (e.g., PersonItem.kt, Loader.kt,DetailScreenItem)
ViewModel Layer:


Handles UI state and logic (e.g., PopularListViewModel.kt, DetailPageViewModel.kt)
Repository Layer:


Abstracts data sources (e.g., MyRepository.kt)
Network Layer:


API interactions using Retrofit (e.g., ApiService.kt)
Paging Layer:


Provides data for infinite scrolling (e.g., PagingSource.kt)
Navigation Flow:
Defined in AppNavigation.kt, managing transitions between the list and detail screens.

4. Package Structure
hilt: For application-level dependency injection (MyApplication, NetworkModule).
model: Data classes representing API responses (e.g., PersonResponse.kt).
navHost: Handles navigation between screens (AppNavigation.kt).
networks: Contains network services (ApiService, MyRepository).
paging: Includes custom PagingSource.kt for infinite scrolling.
screens: UI screens and reusable components.
viewModel: Manages UI logic (PopularListViewModel.kt, DetailPageViewModel.kt).

5. Workflow
Popular People List:
PopularPersonScreen initializes the PopularListViewModel.
The ViewModel calls the repository to fetch data using PagingSource.
Data is displayed in a LazyColumn.
Scrolling triggers pagination requests.
Search Functionality:
Text input updates the query parameter in the ViewModel.
Repository fetches filtered data using PagingSource.
Data is displayed with pagination.
Detail View:
On clicking a person, their Person object is passed via navigation arguments.
DetailScreen fetches and displays the relevant grid data.

6. Design Decisions
MVVM Architecture: Chosen to separate concerns and improve testability.
Paging 3 Library: For efficient, memory-optimized pagination.
Hilt for Dependency Injection: Simplifies object creation and lifecycle management.
Jetpack Compose: Enables declarative UI creation for better maintainability.

7. Error Handling
API Errors: Handled in Repository and surfaced through ViewModel states.
Empty States: Displays "No data found" for empty responses.


