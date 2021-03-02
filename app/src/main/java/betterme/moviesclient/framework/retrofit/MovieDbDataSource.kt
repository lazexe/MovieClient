package betterme.moviesclient.framework.retrofit

import android.annotation.SuppressLint
import betterme.moviesclient.BuildConfig
import betterme.moviesclient.data.abs.RemoteDataSource
import betterme.moviesclient.domain.DATE_FORMAT
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.utils.removeTwoWeeks
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
class MovieDbDataSource(private val api: MovieDbApi) : RemoteDataSource {

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat(DATE_FORMAT)

    override suspend fun refresh(): List<Movie> {
        val twoWeekOldDate = Date().removeTwoWeeks()
        return api.getMovies(dateFormat.format(twoWeekOldDate)).results.map {
            val posterUrl = BuildConfig.MOVIE_DB_POSTER_PATH_BASE_URL + it.posterPath
            Movie(it.id, it.title, it.overview, it.releaseDate, posterUrl)
        }
    }
}