package betterme.moviesclient.usecase.abs

import betterme.moviesclient.domain.Movie

/**
 * Created by Maksym Lazarenko on 2/26/21.
 * Skype: lazexe
 */
interface RemoveFavouriteUseCase {

    suspend fun removeFavourite(movie: Movie)
}