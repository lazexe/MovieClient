package betterme.moviesclient.usecase.impl

import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.usecase.abs.GetFavouritesUseCase

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
class GetFavourites(private val movieRepository: MovieRepository) : GetFavouritesUseCase {

    override fun getFavouritesFlow() = movieRepository.getFavouritesFlow()
}