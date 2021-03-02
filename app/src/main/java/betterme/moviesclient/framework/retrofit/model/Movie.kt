package betterme.moviesclient.framework.retrofit.model

import betterme.moviesclient.domain.Movie
import betterme.moviesclient.framework.CoreMovieConvertible
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
data class Movie(
    @SerializedName("id")           @Expose val id: Int,
    @SerializedName("title")        @Expose val title: String,
    @SerializedName("overview")     @Expose val overview: String,
    @SerializedName("release_date") @Expose val releaseDate: String,
    @SerializedName("poster_path")  @Expose val posterPath: String?,
) : CoreMovieConvertible {

    override fun convert() = Movie(id, title, overview, releaseDate, posterPath)

}