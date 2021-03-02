package betterme.moviesclient.usecase.impl

import betterme.moviesclient.createSimpleMovie
import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.domain.DATE_FORMAT
import betterme.moviesclient.utils.add
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
class GetTwoWeekOldMoviesTest {

    @Mock
    private lateinit var movieRepository: MovieRepository
    private val dateFormat = SimpleDateFormat(DATE_FORMAT)

    private lateinit var testUnit: GetTwoWeekOldMovies

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testUnit = GetTwoWeekOldMovies(movieRepository)
    }

    @Test
    fun `test getTwoWeekOldMoviesFlow() is calling getCachedMoviesFlow in repository`() = runBlocking {
        // given
        val now = Date()
        val releaseDate = dateFormat.format(now)

        val expected = listOf(
            createSimpleMovie(releaseDate = releaseDate),
            createSimpleMovie(releaseDate = releaseDate)
        )

        whenever(movieRepository.getCachedMoviesFlow()).then {
            flow { emit(expected) }
        }

        // when
        val flow = testUnit.getTwoWeekOldMoviesFlow()
        val actual = flow.first()

        // then
        verify(movieRepository).getCachedMoviesFlow()
        assertEquals(expected, actual)
    }

    @Test
    fun `test getTwoWeekOldMoviesFlow() remove movies that two weeks older`() = runBlocking {
        // given
        val now = Date()

        val normalDate = dateFormat.format(now.add(Calendar.DATE, -2))
        val normalMovie = createSimpleMovie(releaseDate = normalDate)

        val normalDate2 = dateFormat.format(now.add(Calendar.DATE, -13))
        val normalMovie2 = createSimpleMovie(releaseDate = normalDate2)

        val expiredDate = dateFormat.format(now.add(Calendar.DATE, -14))
        val expiredMovie = createSimpleMovie(releaseDate = expiredDate)

        val expiredDate2 = dateFormat.format(now.add(Calendar.DATE, -15))
        val expiredMovie2 = createSimpleMovie(releaseDate = expiredDate2)

        val inputMovies = listOf(normalMovie, normalMovie2, expiredMovie, expiredMovie2)
        val expected = listOf(normalMovie, normalMovie2)

        whenever(movieRepository.getCachedMoviesFlow()).then {
            flow { emit(inputMovies) }
        }

        // when
        val flow = testUnit.getTwoWeekOldMoviesFlow()
        val actual = flow.first()

        // then
        assertEquals(expected, actual)
    }
}