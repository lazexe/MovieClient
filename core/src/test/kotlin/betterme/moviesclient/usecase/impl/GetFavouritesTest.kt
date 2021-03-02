package betterme.moviesclient.usecase.impl

import betterme.moviesclient.createSimpleMovieList
import betterme.moviesclient.data.abs.MovieRepository
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
        val expected = createSimpleMovieList()

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