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
    val known_for_department: String,
    val known_for: List<KnownFor>,
)

data class KnownFor(
    val id: Int,
    val title: String?,
    val media_type: String,
    val poster_path: String?,
    val release_date: String?
)
