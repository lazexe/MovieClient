package betterme.moviesclient.usecase.impl

import betterme.moviesclient.data.abs.MovieRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Maksym Lazarenko on 3/1/21.
 * Skype: lazexe
 */
class RefreshMoviesTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var testUnit: RefreshMovies

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testUnit = RefreshMovies(movieRepository, Dispatchers.IO)
    }

    @Test
    fun `test refreshMovies() is calling refreshMovies in repository`() = runBlocking {
        //given
        // when
        testUnit.refreshMovies()

        // then
        verify(movieRepository).refreshMovies()
    }
}