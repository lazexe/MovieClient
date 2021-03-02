package betterme.moviesclient.data

import betterme.moviesclient.createSimpleMovie
import betterme.moviesclient.createSimpleMovieList
import betterme.moviesclient.data.abs.LocalDataSource
import betterme.moviesclient.data.abs.RemoteDataSource
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
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */
class MovieRepositoryImplTest {

    @Mock private lateinit var localDataSource: LocalDataSource
    @Mock private lateinit var remoteDataSource: RemoteDataSource

    private val movieList = createSimpleMovieList()
    private val simpleMovie = createSimpleMovie()

    private lateinit var testUnit: MovieRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testUnit = MovieRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `test refreshMovies() is calling refresh() in remote and cacheMovies() in local data source`() = runBlocking {
        // given
        whenever(remoteDataSource.refresh()).then {
            movieList
        }

        // when
        testUnit.refreshMovies()

        // then
        verify(remoteDataSource).refresh()
        verify(localDataSource).cacheMovies(movieList)
    }

    @Test
    fun `test getCachedMoviesFlow() is calling getCachedMoviesFlow in local data source`() = runBlocking {
        // given
        whenever(localDataSource.getCachedMoviesFlow()).then {
            flow { emit(movieList) }
        }

        // when
        val flow = testUnit.getCachedMoviesFlow()
        val actual = flow.first()

        // then
        verify(localDataSource).getCachedMoviesFlow()
        assertEquals(movieList, actual)
    }

    @Test
    fun `test getFavouritesFlow() is calling getFavouritesFlow in local data source`() = runBlocking {
        // given
        whenever(localDataSource.getFavouritesMoviesFlow()).then {
            flow { emit(movieList) }
        }

        // when
        val flow = testUnit.getFavouritesFlow()
        val actual = flow.first()

        // then
        verify(localDataSource).getFavouritesMoviesFlow()
        assertEquals(movieList, actual)
    }

    @Test
    fun `test addToFavourite() is calling addToFavourite in local data source`() = runBlocking {
        // given
        // when
        testUnit.addToFavourite(simpleMovie)

        // then
        verify(localDataSource).addToFavourite(simpleMovie)
    }

    @Test
    fun `test removeFromFavourite() is calling removeFromFavourite in local data source`() = runBlocking {
        // given
        // when
        testUnit.removeFromFavourite(simpleMovie)

        // then
        verify(localDataSource).removeFromFavourite(simpleMovie)
    }
}