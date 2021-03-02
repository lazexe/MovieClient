package betterme.moviesclient.framework.di

import betterme.moviesclient.presentation.main.MainActivity
import betterme.moviesclient.presentation.favourites.FavouritesMoviesFragment
import betterme.moviesclient.presentation.movies.MoviesFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DatabaseModule::class, RepositoryModule::class, UseCaseModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(fragment: MoviesFragment)

    fun inject(fragment: FavouritesMoviesFragment)
}