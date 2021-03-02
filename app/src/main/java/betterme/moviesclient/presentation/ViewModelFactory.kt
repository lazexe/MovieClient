package betterme.moviesclient.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import betterme.moviesclient.presentation.favourites.FavouritesMoviesViewModel
import betterme.moviesclient.presentation.movies.MoviesViewModel
import betterme.moviesclient.presentation.resources.ResourcesProvider
import betterme.moviesclient.usecase.abs.*
import javax.inject.Inject

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */
class ViewModelFactory @Inject constructor(
        private val twoWeekMoviesUseCase: GetTwoWeekMoviesUseCase,
        private val refreshMoviesUseCase: RefreshMoviesUseCase,
        private val getFavouritesUseCase: GetFavouritesUseCase,
        private val addFavouriteUseCase: AddFavouriteUseCase,
        private val removeFavouriteUseCase: RemoveFavouriteUseCase,
        private val resourcesProvider: ResourcesProvider) :
        ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) return MoviesViewModel(
            refreshMoviesUseCase,
            addFavouriteUseCase,
            removeFavouriteUseCase,
            resourcesProvider,
            twoWeekMoviesUseCase
        ) as T

        if (modelClass.isAssignableFrom(FavouritesMoviesViewModel::class.java)) return FavouritesMoviesViewModel(
            removeFavouriteUseCase,
            getFavouritesUseCase
        ) as T
        throw IllegalArgumentException("ViewModel not found! :(")
    }
}