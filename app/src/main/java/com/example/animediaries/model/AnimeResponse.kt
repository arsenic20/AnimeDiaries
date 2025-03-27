package com.example.animediaries.model

// AnimeResponse.kt
data class AnimeResponse(
    val data: List<Anime>,
    val pagination: Pagination
)

data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val items: PaginationItems?
)

data class PaginationItems(
    val count: Int,
    val total: Int,
    val per_page: Int
)

// Anime.kt
data class Anime(
    val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val images: ImageType,
    val rating: String?,
    val synopsis: String?
)

data class ImageType(
    val jpg: ImageUrls
)

data class ImageUrls(
    val image_url: String,
    val small_image_url: String?,
    val large_image_url: String?
)