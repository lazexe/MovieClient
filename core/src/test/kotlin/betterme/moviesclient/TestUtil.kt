package betterme.moviesclient

import betterme.moviesclient.domain.Movie

/**
 * Created by Maksym Lazarenko on 3/2/21.
 * Skype: lazexe
 */

fun createSimpleMovie(
    id: Int = 1,
    title: String = "Irishman",
    overview: String = "Irishman overview title that contain a long long string",
    releaseDate: String = "2021-03-01",
    posterUrl: String = "https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg",
    favourite: Boolean = false
) = Movie(id, title, overview, releaseDate, posterUrl, favourite)

fun createSimpleMovieList() = listOf(
    Movie(
            234,
            "1917",
            "1917 overview text",
            "2020-01-01",
            "https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg"
        ),
        Movie(
            456,
            "Greenland",
            "Greenland overview text",
            "2020-01-01",
            "https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg"
        ),
        Movie(
            457,
            "Spider-man",
            "Spider-man overview text",
            "2020-01-01",
            "https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg"
        ),
        Movie(
            458,
            "Rock",
            "Rock overview text",
            "2020-01-01",
            "https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg"
        )
)