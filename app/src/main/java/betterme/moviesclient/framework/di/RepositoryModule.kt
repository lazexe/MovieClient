package betterme.moviesclient.framework.di

import betterme.moviesclient.data.abs.LocalDataSource
import betterme.moviesclient.data.abs.MovieRepository
import betterme.moviesclient.data.MovieRepositoryImpl
import betterme.moviesclient.data.abs.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Maksym Lazarenko on 2/25/21.
 * Skype: lazexe
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
    ): MovieRepository = MovieRepositoryImpl(remoteDataSource, localDataSource)
}