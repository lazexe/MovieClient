package betterme.moviesclient.usecase.abs

import betterme.moviesclient.domain.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Maksym Lazarenko on 2/26/21.
 * Skype: lazexe
 */
interface GetFavouritesUseCase {

    fun getFavouritesFlow(): Flow<List<Movie>>
}