package betterme.moviesclient.framework

import betterme.moviesclient.domain.Movie

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */

interface CoreMovieConvertible {
    fun convert(): Movie
}