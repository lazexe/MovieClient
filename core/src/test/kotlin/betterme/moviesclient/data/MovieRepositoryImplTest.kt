package betterme.moviesclient.data

import betterme.moviesclient.data.abs.LocalDataSource
import betterme.moviesclient.data.abs.RemoteDataSource
import betterme.moviesclient.domain.Movie
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

    private val simpleMovie = Movie(123, "Irishman", "Irishman overview text", "2020-01-01","https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg")

    private val simpleMovieList = listOf(
            simpleMovie,
            Movie(234, "1917", "1917 overview text", "2020-01-01", "https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg"),
            Movie(456, "Greenland", "Greenland overview text", "2020-01-01", "https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg"),
            Movie(457, "Spider-man", "Spider-man overview text", "2020-01-01", "https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg"),
            Movie(458, "Rock", "Rock overview text", "2020-01-01", "https://image.tmdb.org/t/p/w500/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg")
    )

    @Mock
    private lateinit var localDataSource: LocalDataSource
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

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
            simpleMovieList
        }

        // when
        testUnit.refreshMovies()

        // then
        verify(remoteDataSource).refresh()
        verify(localDataSource).cacheMovies(simpleMovieList)
    }

    @Test
    fun `test getCachedMoviesFlow() is calling getCachedMoviesFlow in local data source`() = runBlocking {
        // given
        whenever(localDataSource.getCachedMoviesFlow()).then {
            flow { emit(simpleMovieList) }
        }

        // when
        val flow = testUnit.getCachedMoviesFlow()
        val actual = flow.first()

        // then
        verify(localDataSource).getCachedMoviesFlow()
        assertEquals(simpleMovieList, actual)
    }

    @Test
    fun `test getFavouritesFlow() is calling getFavouritesFlow in local data source`() = runBlocking {
        // given
        whenever(localDataSource.getFavouritesMoviesFlow()).then {
            flow { emit(simpleMovieList) }
        }

        // when
        val flow = testUnit.getFavouritesFlow()
        val actual = flow.first()

        // then
        verify(localDataSource).getFavouritesMoviesFlow()
        assertEquals(simpleMovieList, actual)
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