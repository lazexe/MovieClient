package betterme.moviesclient.presentation.favourites

import betterme.moviesclient.createSimpleMovie
import betterme.moviesclient.usecase.abs.GetFavouritesUseCase
import betterme.moviesclient.usecase.abs.RemoveFavouriteUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Maksym Lazarenko on 3/1/21.
 * Skype: lazexe
 */
class FavouritesMoviesViewModelTest {

    private val simpleMovie = createSimpleMovie()

    @Mock private lateinit var getFavouritesUseCase: GetFavouritesUseCase
    @Mock private lateinit var removeFavouriteUseCase: RemoveFavouriteUseCase

    private lateinit var testUnit: FavouritesMoviesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        whenever(getFavouritesUseCase.getFavouritesFlow()).then {
            flow { emit(listOf(simpleMovie)) }
        }
        testUnit = FavouritesMoviesViewModel(removeFavouriteUseCase, getFavouritesUseCase)
    }

    @Test
    fun `test getFavouritesFlow() is calling when viewModel created`() {
        // given
        // when
        // then
        verify(getFavouritesUseCase).getFavouritesFlow()
    }

    @Test
    fun `test removeFromFavourites() is calling removeFavourite in usecase`() = runBlocking {
        //given

        // when
        testUnit.removeFromFavourites(simpleMovie)

        // then
        verify(removeFavouriteUseCase).removeFavourite(simpleMovie)
    }
}