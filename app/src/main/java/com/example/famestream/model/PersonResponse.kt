package com.example.famestream.model

data class PersonResponse(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int,
    val total_results: Int
)

data class Person(
    val id: Int,
    val name: String,
    val profile_path: String?,
    val known_for_department: Double,
    val known_for: List<KnownFor>
)

data class KnownFor(
    val id: Int,
    val title: String?,  // Title for movies
    val name: String?,   // Name for TV shows
    val media_type: String, // "movie" or "tv"
    val poster_path: String?
)
