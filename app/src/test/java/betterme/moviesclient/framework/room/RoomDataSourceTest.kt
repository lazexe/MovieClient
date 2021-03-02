package betterme.moviesclient.framework.room

import betterme.moviesclient.createSimpleDatabaseMovie
import betterme.moviesclient.createSimpleDatabaseMovieList
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Maksym Lazarenko on 3/1/21.
 * Skype: lazexe
 */
class RoomDataSourceTest {

    private val simpleDatabaseMovie = createSimpleDatabaseMovie()
    private val databaseMovieList = createSimpleDatabaseMovieList()

    @Mock private lateinit var movieDatabase: MovieDatabase
    @Mock private lateinit var movieDao: MovieDao

    private lateinit var testUnit: RoomDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        whenever(movieDatabase.movieDao()).then {
            movieDao
        }
        testUnit = RoomDataSource(movieDatabase)
    }

    @Test
    fun `test getCachedMoviesFlow() is calling getMoviesFlow in dao`() = runBlocking {
        // given
        whenever(movieDao.getMoviesFlow()).then {
            flow { emit(databaseMovieList) }
        }
        val expected = databaseMovieList.map { it.convert() }


        // when
        val flow = testUnit.getCachedMoviesFlow()
        val actual = flow.first()

        // then
        verify(movieDao).getMoviesFlow()
        assertEquals(expected, actual)
    }

    @Test
    fun `test getFavouritesFlow is calling getFavouritesFlow in dao`() = runBlocking {
        // given
        whenever(movieDao.getFavouritesFlow()).then {
            flow { emit(databaseMovieList) }
        }
        val expected = databaseMovieList.map { it.convert() }

        // when
        val flow = testUnit.getFavouritesMoviesFlow()
        val actual = flow.first()

        // then
        verify(movieDao).getFavouritesFlow()
        assertEquals(expected, actual)
    }

    @Test
    fun `test cacheMovies() is calling cacheMovies in dao`() = runBlocking {
        // given
        val input = databaseMovieList.map { it.convert() }

        // when
        testUnit.cacheMovies(input)

        // then
        verify(movieDao).cacheMovies(databaseMovieList)
    }

    @Test
    fun `test addToFavourite is calling setFavourite in dao`() = runBlocking {
        // given
        // when
        testUnit.addToFavourite(simpleDatabaseMovie.convert())
        // then
        verify(movieDao).setFavourite(simpleDatabaseMovie.id)
    }

    @Test
    fun `test removeFromFavourite is calling removeFavourite in dao`() = runBlocking {
        // given
        // when
        testUnit.removeFromFavourite(simpleDatabaseMovie.convert())
        // then
        verify(movieDao).removeFavourite(simpleDatabaseMovie.id)
    }
}