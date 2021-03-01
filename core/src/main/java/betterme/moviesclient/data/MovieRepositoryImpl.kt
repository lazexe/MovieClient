package betterme.moviesclient.data

import betterme.moviesclient.data.abs.LocalDataSource
import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.data.abs.RemoteDataSource
import betterme.moviesclient.domain.Movie

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
class MovieRepositoryImpl(
        private val remote: RemoteDataSource,
        private val local: LocalDataSource
) : MovieRepository {

    override suspend fun refreshMovies() {
        val movies = remote.refresh()
        local.cacheMovies(movies)
    }

    override fun getCachedMoviesFlow() = local.getCachedMoviesFlow()

    override fun getFavouritesFlow() = local.getFavouritesMoviesFlow()

    override suspend fun addToFavourite(movie: Movie) = local.addToFavourite(movie)

    override suspend fun removeFromFavourite(movie: Movie) = local.removeFromFavourite(movie)
}