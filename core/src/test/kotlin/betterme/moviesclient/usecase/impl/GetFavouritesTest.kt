package betterme.moviesclient.usecase.impl

import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.domain.Movie
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Maksym Lazarenko on 3/1/21.
 * Skype: lazexe
 */
class GetFavouritesTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var testUnit: GetFavourites

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testUnit = GetFavourites(movieRepository)
    }

    @Test
    fun `test getFavouritesFlow() is calling getFavouritesFlow in repository`() = runBlocking {
        // given
        val expected = listOf(
            Movie(123, "Irishman", "Irishman overview text", "2020-01-01","https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg"),
            Movie(234, "1917", "1917 overview text", "2020-01-01", "https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg"),
        )

        whenever(movieRepository.getFavouritesFlow()).then {
            flow { emit(expected) }
        }

        // when
        val flow = testUnit.getFavouritesFlow()
        val actual = flow.first()

        // then
        verify(movieRepository).getFavouritesFlow()
        assertEquals(expected, actual)
    }
}