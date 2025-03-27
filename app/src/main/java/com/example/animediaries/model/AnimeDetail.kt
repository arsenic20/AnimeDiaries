package com.example.animediaries.model

data class AnimeDetailResponse(
    val data: AnimeDetail
)

data class AnimeDetail(
    val mal_id: Int,
    val url: String,
    val images: ImageType,
    val trailer: AnimeTrailer?,
    val title: String,
    val title_english: String?,
    val title_japanese: String?,
    val type: String?,
    val source: String?,
    val episodes: Int?,
    val status: String?,
    val rating: String?,
    val score: Double?,
    val scored_by: Int?,
    val rank: Int?,
    val popularity: Int?,
    val members: Int?,
    val favorites: Int?,
    val synopsis: String?,
    val background: String?,
    val season: String?,
    val year: Int?,
    val genres: List<Genre>
)

data class AnimeTrailer(
    val youtube_id: String?,
    val url: String?,
    val embed_url: String?,
    val images: TrailerImages?
)

data class TrailerImages(
    val image_url: String?,
    val small_image_url: String?,
    val medium_image_url: String?,
    val large_image_url: String?,
    val maximum_image_url: String?
)

data class Genre(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)