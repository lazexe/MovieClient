package betterme.moviesclient.framework.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.framework.CoreMovieConvertible

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */

@Entity(tableName = "movie")
data class Movie(
        @PrimaryKey
        @ColumnInfo(name = "id")            val id: Int,
        @ColumnInfo(name = "title")         val title: String,
        @ColumnInfo(name = "overview")      val overview: String,
        @ColumnInfo(name = "release_date")  val releaseDate: String,
        @ColumnInfo(name = "poster_url")    val posterUrl: String?,
        @ColumnInfo(name = "cached")        val cached: Boolean,
        @ColumnInfo(name = "favourite")     val favourite: Boolean
) : CoreMovieConvertible {

    override fun convert() = Movie(id, title, overview, releaseDate, posterUrl, favourite)
}