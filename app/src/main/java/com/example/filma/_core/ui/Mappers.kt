package com.example.filma._core.ui

import com.example.filma._core.data.api.model.ActorDTO
import com.example.filma._core.data.api.model.MovieDTO
import com.example.filma._core.data.api.model.MovieDetailsDTO
import com.example.filma._core.ui.model.Actor
import com.example.filma._core.ui.model.Movie
import com.example.filma._core.ui.model.MovieDetails

object MovieDtoToUiMapper {
    operator fun invoke(
        movieList: List<MovieDTO>
    ): List<Movie> {
        return movieList.map { movieDTO ->
            Movie(
                id = movieDTO.id,
                title = movieDTO.title,
                imageUrl = movieDTO.imageUrl,
                imDbRating = if (movieDTO.imDbRating.isNullOrEmpty()) {
                    " - "
                } else {
                    movieDTO.imDbRating
                }
            )
        }
    }
}

object MovieDetailsDtoToUiMapper {
    operator fun invoke(
        movieDetails: MovieDetailsDTO
    ): MovieDetails {
        return MovieDetails(
            id = movieDetails.id,
            title = movieDetails.title,
            originalTitle = movieDetails.originalTitle,
            fullTitle = movieDetails.fullTitle,
            type = movieDetails.type,
            year = movieDetails.year,
            imageUrl = movieDetails.imageUrl,
            releaseDate = movieDetails.releaseDate,
            runtimeMins = movieDetails.runtimeMins,
            runtimeStr = movieDetails.runtimeStr,
            plot = movieDetails.plot,
            plotLocal = movieDetails.plotLocal,
            plotLocalIsRtl = movieDetails.plotLocalIsRtl,
            directors = movieDetails.directors,
            actorList = ActorDtoToUiMapper(movieDetails.actorList),
            genres = movieDetails.genres,
            imDbRating = if (movieDetails.imDbRating.isNullOrEmpty()) {
                " - "
            } else {
                movieDetails.imDbRating
            },
            keywords = movieDetails.keywords,
            keywordList = movieDetails.keywordList,
            similars = MovieDtoToUiMapper(movieDetails.similars)
        )
    }
}

object ActorDtoToUiMapper {
    operator fun invoke(
        actorList: List<ActorDTO>
    ): List<Actor> {
        return actorList.map { actorDTO ->
            Actor(
                id = actorDTO.id,
                imageUrl = actorDTO.imageUrl,
                name = actorDTO.name,
                asCharacter = actorDTO.asCharacter
            )
        }
    }
}