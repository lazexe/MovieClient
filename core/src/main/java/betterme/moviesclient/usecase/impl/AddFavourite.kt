package betterme.moviesclient.usecase.impl

import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.usecase.abs.AddFavouriteUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
class AddFavourite(private val movieRepository: MovieRepository, private val dispatcher: CoroutineDispatcher) : AddFavouriteUseCase {

    override suspend fun addToFavourite(movie: Movie) {
        withContext(dispatcher) {
            movieRepository.addToFavourite(movie)
        }
    }
}