package betterme.moviesclient.presentation.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.usecase.abs.GetFavouritesUseCase
import betterme.moviesclient.usecase.abs.RemoveFavouriteUseCase
import kotlinx.coroutines.launch

class FavouritesMoviesViewModel(
        private val removeFavouriteUseCase: RemoveFavouriteUseCase,
        getFavouritesUseCase: GetFavouritesUseCase
        ) : ViewModel() {

    val favouritesMoviesLiveData: LiveData<List<Movie>> = getFavouritesUseCase.getFavouritesFlow().asLiveData()

    fun removeFromFavourites(movie: Movie) {
        viewModelScope.launch {
            removeFavouriteUseCase.removeFavourite(movie)
        }
    }
}