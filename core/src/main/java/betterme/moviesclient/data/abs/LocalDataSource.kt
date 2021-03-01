package betterme.moviesclient.data.abs

import betterme.moviesclient.domain.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
interface LocalDataSource {

    suspend fun cacheMovies(movies: List<Movie>)

    fun getCachedMoviesFlow(): Flow<List<Movie>>

    fun getFavouritesMoviesFlow(): Flow<List<Movie>>

    suspend fun addToFavourite(movie: Movie)

    suspend fun removeFromFavourite(movie: Movie)
}