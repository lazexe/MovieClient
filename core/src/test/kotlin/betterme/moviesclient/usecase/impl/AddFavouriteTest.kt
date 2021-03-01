package betterme.moviesclient.usecase.impl

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

class AddFavouriteTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var testUnit: AddFavourite

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testUnit = AddFavourite(movieRepository, Dispatchers.IO)
    }

    @Test
    fun `test addToFavourite() is calling addToFavourite in repository`() = runBlocking {
        // given
        val movie = Movie(1, "Title", "Overview", "2020-01-01", "http://google.com")

        // when
        testUnit.addToFavourite(movie)

        // then
        verify(movieRepository).addToFavourite(movie)
    }
}