package betterme.moviesclient.domain

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */

const val DATE_FORMAT = "yyyy-MM-dd"

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterUrl: String?
) {
    @Suppress("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat(DATE_FORMAT)

    fun getReleaseDate(): Date = dateFormat.parse(releaseDate)
}