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
                title = movieDTO.name ?: movieDTO.alternativeName,
                alternativeTitle = movieDTO.alternativeName,
                imageUrl = movieDTO.poster.previewUrl,
                imDbRating = movieDTO.rating.imdb.let {
                    if (it == 0.0) {
                        "-"
                    } else {
                        it.toString()
                    }
                },
                kinopoiskRating = movieDTO.rating.kp.let {
                    if (it == 0.0) {
                        "-"
                    } else {
                        it.toString()
                    }
                },
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
            name = detailsMovie.name,
            description = detailsMovie.description,
            slogan = detailsMovie.slogan.orEmpty(),
            year = detailsMovie.year,
            posterUrl = detailsMovie.poster.previewUrl,
            kinopoiskRating = detailsMovie.rating.kp.let {
                if (it == 0.0) {
                    "-"
                } else {
                    it.toString()
                }
            },
            imDbRating = detailsMovie.rating.imdb.let {
                if (it == 0.0) {
                    "-"
                } else {
                    it.toString()
                }
            },
            videoTrailerUrl = detailsMovie.videos.trailers[0].url,
            movieLength = detailsMovie.movieLength.minutes.toString(),
            facts = detailsMovie.facts.map { it.value }.toList(),
            genres = detailsMovie.genres.map { it.name }.toList(),
            countries = detailsMovie.countries.map { it.name }.toList(),
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
                name = personDTO.name,
                enName = personDTO.enName,
                photoUrl = personDTO.photoUrl,
                enProfession = personDTO.enProfession,
                description = personDTO.description
            )
        }

        return persons.filter { it.enProfession == profession }.toList()
    }
}