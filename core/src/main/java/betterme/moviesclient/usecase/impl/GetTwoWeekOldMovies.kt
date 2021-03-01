package betterme.moviesclient.usecase.impl

import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.usecase.abs.GetTwoWeekMoviesUseCase
import betterme.moviesclient.utils.add
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
class GetTwoWeekOldMovies(private val movieRepository: MovieRepository) : GetTwoWeekMoviesUseCase {

    override fun getTwoWeekOldMoviesFlow(): Flow<List<Movie>> {
        val now = Date()
        val twoWeekOldDate = now.add(Calendar.DATE, -14)

        return movieRepository.getCachedMoviesFlow().map { movies ->
            movies.filter { movie ->
                movie.getReleaseDate().after(twoWeekOldDate)
            }
        }
    }
}