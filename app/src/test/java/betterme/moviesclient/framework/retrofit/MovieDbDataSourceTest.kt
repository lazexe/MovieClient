package betterme.moviesclient.framework.retrofit

import betterme.moviesclient.BuildConfig.MOVIE_DB_POSTER_PATH_BASE_URL
import betterme.moviesclient.createSimpleApiMovieList
import betterme.moviesclient.domain.DATE_FORMAT
import betterme.moviesclient.framework.retrofit.model.MoviesResponse
import betterme.moviesclient.utils.removeTwoWeeks
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Maksym Lazarenko on 3/1/21.
 * Skype: lazexe
 */
class MovieDbDataSourceTest {

    @Mock
    private lateinit var api: MovieDbApi

    private lateinit var testUnit: MovieDbDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testUnit = MovieDbDataSource(api)
    }

    @Test
    fun testRefresh() = runBlocking {
        // given
        val apiMovieList = createSimpleApiMovieList()

        val twoWeekOld = Date().removeTwoWeeks()
        val dateFormat = SimpleDateFormat(DATE_FORMAT)
        whenever(api.getMovies(dateFormat.format(twoWeekOld))).then {
            MoviesResponse(1, apiMovieList, 1, 1)
        }
        val expected = mutableListOf<betterme.moviesclient.domain.Movie>()
        apiMovieList.forEach {
            expected.add(
                betterme.moviesclient.domain.Movie(
                    it.id,
                    it.title,
                    it.overview,
                    it.releaseDate,
                    MOVIE_DB_POSTER_PATH_BASE_URL + it.posterPath
                )
            )
        }

        // when
        val actual = testUnit.refresh()

        // then
        verify(api).getMovies(dateFormat.format(twoWeekOld))
        assertEquals(expected, actual)
    }
}