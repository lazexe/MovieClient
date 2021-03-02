package betterme.moviesclient.framework.room

import betterme.moviesclient.data.abs.LocalDataSource
import betterme.moviesclient.domain.Movie
import kotlinx.coroutines.flow.map

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */

class RoomDataSource(private val movieDatabase: MovieDatabase) : LocalDataSource {

    override fun getCachedMoviesFlow() = movieDatabase.movieDao().getMoviesFlow().map { localMovies ->
        localMovies.map {
            it.convert()
        }
    }

    override fun getFavouritesMoviesFlow() = movieDatabase.movieDao().getFavouritesFlow().map { localMovies ->
        localMovies.map {
            it.convert()
        }
    }

    override suspend fun cacheMovies(movies: List<Movie>) {
        val favourites = movieDatabase.movieDao().getFavourites()
        val moviesToInsert = movies.map { newMovie ->
            val favourite = favourites.find { favourite -> favourite.id == newMovie.id }

            Movie(
                newMovie.id,
                newMovie.title,
                newMovie.overview,
                newMovie.releaseDate,
                newMovie.posterUrl,
                cached = true,
                favourite = favourite != null
            )
        }
        movieDatabase.movieDao().cacheMovies(moviesToInsert)
    }

    override suspend fun addToFavourite(movie: Movie) {
        movieDatabase.movieDao().setFavourite(movie.id)
    }

    override suspend fun removeFromFavourite(movie: Movie) {
        movieDatabase.movieDao().removeFavourite(movie.id)
    }
}