package betterme.moviesclient.presentation.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import betterme.moviesclient.createSimpleMovie
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.presentation.NetworkStatus
import betterme.moviesclient.presentation.TestCoroutineRule
import betterme.moviesclient.presentation.resources.ResourcesProvider
import betterme.moviesclient.usecase.abs.AddFavouriteUseCase
import betterme.moviesclient.usecase.abs.GetTwoWeekMoviesUseCase
import betterme.moviesclient.usecase.abs.RefreshMoviesUseCase
import betterme.moviesclient.usecase.abs.RemoveFavouriteUseCase
import com.nhaarman.mockitokotlin2.atLeast
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Maksym Lazarenko on 3/1/21.
 * Skype: lazexe
 */

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val simpleMovie = createSimpleMovie()

    private lateinit var testUnit: MoviesViewModel

    @Mock private lateinit var twoWeekMoviesUseCase: GetTwoWeekMoviesUseCase
    @Mock private lateinit var refreshMoviesUseCase: RefreshMoviesUseCase
    @Mock private lateinit var addToFavouritesUseCase: AddFavouriteUseCase
    @Mock private lateinit var removeFavouriteUseCase: RemoveFavouriteUseCase
    @Mock private lateinit var resourcesProvider: ResourcesProvider

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        whenever(twoWeekMoviesUseCase.getTwoWeekOldMoviesFlow()).then {
            flow { emit(listOf<Movie>()) }
        }
        testUnit = MoviesViewModel(
            refreshMoviesUseCase,
            addToFavouritesUseCase,
            removeFavouriteUseCase,
            resourcesProvider,
            twoWeekMoviesUseCase
        )
    }

    @Test
    fun `test getTwoWeekOldMovies() is calling when viewModel created`() {
        testCoroutineRule.runBlockingTest {
            // given
            // when
            // then
            verify(twoWeekMoviesUseCase).getTwoWeekOldMoviesFlow()
        }
    }

    @Test
    fun `test refreshMovies() is calling in refreshUseCase when viewModel is created`() {
        testCoroutineRule.runBlockingTest {
            // given
            // when
            // then
            verify(refreshMoviesUseCase).refreshMovies()
        }
    }

    @Test
    fun `test handleFavouriteClick() with favourite movie is calling removeFavourite in usecase`() {
        testCoroutineRule.runBlockingTest {
            // given
            simpleMovie.favourite = true
            // when
            testUnit.handleFavouriteClicked(simpleMovie)
            // then
            verify(removeFavouriteUseCase).removeFavourite(simpleMovie)
        }
    }

    @Test
    fun `test handleFavouriteClick() with non-favourite movie is calling addFavourite in usecase`() {
        testCoroutineRule.runBlockingTest {
            // given
            simpleMovie.favourite = false
            // when
            testUnit.handleFavouriteClicked(simpleMovie)
            // then
            verify(addToFavouritesUseCase).addToFavourite(simpleMovie)
        }
    }

    @Test
    fun `test refreshMovies() with OK server response`() {
        testCoroutineRule.runBlockingTest {
            // given
            val expected = NetworkStatus.Success

            // when
            testUnit.refreshMovies()

            // then
            verify(refreshMoviesUseCase, atLeast(1)).refreshMovies()
            val actual = testUnit.networkStatus.value
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `test refreshMovies() with failed server response`() {
        testCoroutineRule.runBlockingTest {
            // given
            val errorMessage = "Network exception"
            whenever(refreshMoviesUseCase.refreshMovies()).thenThrow(RuntimeException(errorMessage))
            val expected = NetworkStatus.Failed(errorMessage)

            // when
            testUnit.refreshMovies()

            // then
            verify(refreshMoviesUseCase, atLeast(1)).refreshMovies()
            val actual = testUnit.networkStatus.value
            assertEquals(expected, actual)
        }
    }
}