package betterme.moviesclient.presentation.movies

import androidx.lifecycle.*
import betterme.moviesclient.R
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.presentation.NetworkStatus
import betterme.moviesclient.presentation.resources.ResourcesProvider
import betterme.moviesclient.usecase.abs.*
import kotlinx.coroutines.launch

/**
 * Created by Maksym Lazarenko on 3/1/21.
 * Skype: lazexe
 */

class MoviesViewModel(
    private val refreshMoviesUseCase: RefreshMoviesUseCase,
    private val addToFavouritesUseCase: AddFavouriteUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase,
    private val resourcesProvider: ResourcesProvider,
    twoWeekMoviesUseCase: GetTwoWeekMoviesUseCase
        ) : ViewModel() {

    private val _networkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus get() = _networkStatus

    val moviesLiveData: LiveData<List<Movie>> = twoWeekMoviesUseCase.getTwoWeekOldMoviesFlow().asLiveData()

    init {
        refreshMovies()
    }

    fun refreshMovies() {
        viewModelScope.launch {
            _networkStatus.value = NetworkStatus.InProgress
            try {
                refreshMoviesUseCase.refreshMovies()
                _networkStatus.value = NetworkStatus.Success
            } catch (e: Exception) {
                val errorMessage = e.localizedMessage ?: resourcesProvider.getString(R.string.error_unknown)
                _networkStatus.value = NetworkStatus.Failed(errorMessage)
            }
        }
    }

    fun handleFavouriteClicked(movie: Movie) {
        viewModelScope.launch {
            if (movie.favourite)
                removeFavouriteUseCase.removeFavourite(movie)
            else
                addToFavouritesUseCase.addToFavourite(movie)

        }
    }
}