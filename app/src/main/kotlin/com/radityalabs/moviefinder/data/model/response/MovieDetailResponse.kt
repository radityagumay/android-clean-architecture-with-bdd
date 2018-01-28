package com.radityalabs.moviefinder.data.model.response

import com.google.gson.annotations.SerializedName

sealed class MovieDetail {
    data class Response(
            @SerializedName("adult") val adult: Boolean?,
            @SerializedName("backdrop_path") val backdropPath: String?,
            @SerializedName("belongs_to_collection") val belongsToCollection: BelongsToCollection?,
            @SerializedName("budget") val budget: Int?,
            @SerializedName("genres") val genres: List<Genre?>?,
            @SerializedName("homepage") val homepage: String?,
            @SerializedName("id") val id: Int?,
            @SerializedName("imdb_id") val imdbId: String?,
            @SerializedName("original_language") val originalLanguage: String?,
            @SerializedName("original_title") val originalTitle: String?,
            @SerializedName("overview") val overview: String?,
            @SerializedName("popularity") val popularity: Double?,
            @SerializedName("poster_path") val posterPath: String?,
            @SerializedName("production_companies") val productionCompanies: List<ProductionCompany?>?,
            @SerializedName("production_countries") val productionCountries: List<ProductionCountry?>?,
            @SerializedName("release_date") val releaseDate: String?,
            @SerializedName("revenue") val revenue: Int?,
            @SerializedName("runtime") val runtime: Int?,
            @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage?>?,
            @SerializedName("status") val status: String?,
            @SerializedName("tagline") val tagline: String?,
            @SerializedName("title") val title: String?,
            @SerializedName("video") val video: Boolean?,
            @SerializedName("vote_average") val voteAverage: Float?,
            @SerializedName("vote_count") val voteCount: Int?,
            @SerializedName("videos") val videos: Videos?,
            val appendedGenre: String? = null
    )

    data class SpokenLanguage(
            @SerializedName("iso_639_1") val iso6391: String?,
            @SerializedName("name") val name: String?
    )

    data class ProductionCompany(
            @SerializedName("name") val name: String?,
            @SerializedName("id") val id: Int?
    )

    data class ProductionCountry(
            @SerializedName("iso_3166_1") val iso31661: String?,
            @SerializedName("name") val name: String?
    )

    data class Genre(
            @SerializedName("id") val id: Int?,
            @SerializedName("name") val name: String?
    )

    data class Videos(
            @SerializedName("results") val results: List<Result?>?
    )

    data class Result(
            @SerializedName("id") val id: String?,
            @SerializedName("iso_639_1") val iso6391: String?,
            @SerializedName("iso_3166_1") val iso31661: String?,
            @SerializedName("key") val key: String?,
            @SerializedName("name") val name: String?,
            @SerializedName("site") val site: String?,
            @SerializedName("size") val size: Int?,
            @SerializedName("type") val type: String?
    )

    data class BelongsToCollection(
            @SerializedName("id") val id: Int?,
            @SerializedName("name") val name: String?,
            @SerializedName("poster_path") val posterPath: String?,
            @SerializedName("backdrop_path") val backdropPath: String?
    )
}
