package betterme.moviesclient.framework.di

import android.content.Context
import betterme.moviesclient.MovieApplication
import betterme.moviesclient.presentation.resources.AppResourceProvider
import betterme.moviesclient.presentation.resources.ResourcesProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */
@Module
class ApplicationModule(private val app: MovieApplication) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideResourceProvider(): ResourcesProvider = AppResourceProvider(app.resources)
}