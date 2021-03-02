package betterme.moviesclient.usecase.impl

import betterme.moviesclient.createSimpleMovie
import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.domain.Movie
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
class RemoveFavouriteTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var testUnit: RemoveFavourite

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testUnit = RemoveFavourite(movieRepository, Dispatchers.IO)
    }

    @Test
    fun `test removeFavourite() is calling removeFromFavourite in repository`() = runBlocking {
        // given
        val movie = createSimpleMovie()

        // when
        testUnit.removeFavourite(movie)

        // then
        verify(movieRepository).removeFromFavourite(movie)
    }

}