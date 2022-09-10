package com.example.filma._core.ui

import com.example.filma.PERSON_PROFESSION_ACTOR
import com.example.filma.PERSON_PROFESSION_DIRECTOR
import com.example.filma._core.data.api.model.*
import com.example.filma._core.ui.model.DetailsMovie
import com.example.filma._core.ui.model.Movie
import com.example.filma._core.ui.model.Person
import kotlin.time.Duration.Companion.minutes

object MapperMovieDtoToUi {
    operator fun invoke(
        movieList: List<MovieFromListDTO>
    ): List<Movie> {
        return movieList.map { movieDTO ->
            Movie(
                id = movieDTO.id.toString(),
                title = movieDTO.name ?: movieDTO.alternativeName.orEmpty() ,
                alternativeTitle = movieDTO.alternativeName.orEmpty(),
                imageUrl = movieDTO.poster.previewUrl,
                imDbRating = getVerifiedRating(movieDTO.rating.imdb),
                kinopoiskRating = getVerifiedRating(movieDTO.rating.kp),
                year = movieDTO.year.toString()
            )
        }
    }
}

object MapperDetailsMovieDtoToUi {
    operator fun invoke(
        detailsMovie: DetailsMovieDTO
    ): DetailsMovie {
        return DetailsMovie(
            id = detailsMovie.id,
            imdbId = detailsMovie.externalId.imdb.orEmpty(),
            type = detailsMovie.type,
            name = detailsMovie.name.orEmpty(),
            description = detailsMovie.description.orEmpty(),
            slogan = detailsMovie.slogan.orEmpty(),
            year = detailsMovie.year.toString(),
            posterUrl = detailsMovie.poster.previewUrl,
            kinopoiskRating = getVerifiedRating(detailsMovie.rating.kp),
            imDbRating = getVerifiedRating(detailsMovie.rating.imdb),
            videoTrailerUrl = detailsMovie.videos.trailers?.firstOrNull()?.url,
            movieLength = getFormattedMovieLength(detailsMovie.movieLength),
            facts = detailsMovie.facts.map { it.value }.toList(),
            genres = detailsMovie.genres.map { it.name }.toString().removeSurrounding("[", "]"),
            countries = detailsMovie.countries.map { it.name }.toString().removeSurrounding("[", "]"),
            directors = MapperPersonDTOToUi(detailsMovie.persons, PERSON_PROFESSION_DIRECTOR),
            actors = MapperPersonDTOToUi(detailsMovie.persons, PERSON_PROFESSION_ACTOR)
        )
    }
}

object MapperPersonDTOToUi {
    operator fun invoke(list: List<PersonDTO>, profession: String): List<Person> {
        val persons = list.map { personDTO ->
            Person(
                id = personDTO.id,
                name = if (personDTO.name.isNullOrEmpty()) personDTO.enName.orEmpty() else personDTO.name,
                enName = personDTO.enName.orEmpty(),
                photoUrl = personDTO.photoUrl,
                enProfession = personDTO.enProfession,
                description = personDTO.description
            )
        }

        return persons.filter { it.enProfession == profession }.toList()
    }
}

private fun getVerifiedRating(rating: Double): String = if (rating == 0.0) "-" else rating.toString()

private fun getFormattedMovieLength(length: Int): String = if (length == 0) "-" else length.minutes.toString().replace('h', 'ч').replace('m', 'м')