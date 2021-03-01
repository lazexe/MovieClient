package betterme.moviesclient.usecase.impl

import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.usecase.abs.RemoveFavouriteUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
class RemoveFavourite(private val movieRepository: MovieRepository, private val dispatcher: CoroutineDispatcher) : RemoveFavouriteUseCase{

    override suspend fun removeFavourite(movie: Movie) {
        withContext(dispatcher) {
            movieRepository.removeFromFavourite(movie)
        }
    }
}