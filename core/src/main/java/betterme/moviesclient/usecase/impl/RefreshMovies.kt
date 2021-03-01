package betterme.moviesclient.usecase.impl

import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.usecase.abs.RefreshMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
class RefreshMovies(private val movieRepository: MovieRepository, private val dispatcher: CoroutineDispatcher) : RefreshMoviesUseCase {

    override suspend fun refreshMovies() {
        withContext(dispatcher) {
            movieRepository.refreshMovies()
        }
    }
}