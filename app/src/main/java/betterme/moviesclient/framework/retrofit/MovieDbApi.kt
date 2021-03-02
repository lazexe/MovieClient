package betterme.moviesclient.framework.retrofit

import betterme.moviesclient.BuildConfig
import betterme.moviesclient.framework.retrofit.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
interface MovieDbApi {

    @GET("discover/movie?api_key=${BuildConfig.MOVIE_DB_API_KEY}")
    suspend fun getMovies(@Query("primary_release_date.gte") dateFrom: String): MoviesResponse
}