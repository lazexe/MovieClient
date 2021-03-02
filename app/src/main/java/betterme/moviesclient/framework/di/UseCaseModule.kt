package betterme.moviesclient.framework.di

import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.usecase.abs.*
import betterme.moviesclient.usecase.impl.*
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by Maksym Lazarenko on 2/26/21.
 * Skype: lazexe
 */
@Module
class UseCaseModule {

    private val ioDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideAddFavouriteUseCase(movieRepository: MovieRepository): AddFavouriteUseCase = AddFavourite(movieRepository, ioDispatcher)

    @Provides
    @Singleton
    fun provideRemoveFavouriteUseCase(movieRepository: MovieRepository): RemoveFavouriteUseCase = RemoveFavourite(movieRepository, ioDispatcher)

    @Provides
    @Singleton
    fun provideGetFavouritesUseCase(movieRepository: MovieRepository): GetFavouritesUseCase = GetFavourites(movieRepository)

    @Provides
    @Singleton
    fun provideGetTwoWeekMoviesUseCase(movieRepository: MovieRepository): GetTwoWeekMoviesUseCase = GetTwoWeekOldMovies(movieRepository)

    @Provides
    @Singleton
    fun provideRefreshMoviesUseCase(movieRepository: MovieRepository): RefreshMoviesUseCase = RefreshMovies(movieRepository, ioDispatcher)
}