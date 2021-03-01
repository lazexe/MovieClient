package betterme.moviesclient.data.abs

import betterme.moviesclient.domain.Movie

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
interface RemoteDataSource {

    suspend fun refresh(): List<Movie>
}