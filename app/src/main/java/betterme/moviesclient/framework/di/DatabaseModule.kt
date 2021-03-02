package betterme.moviesclient.framework.di

import android.content.Context
import androidx.room.Room
import betterme.moviesclient.data.abs.LocalDataSource
import betterme.moviesclient.framework.room.MovieDatabase
import betterme.moviesclient.framework.room.RoomDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(context: Context) =
        Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db").build()

    @Provides
    @Singleton
    fun provideLocalDataSource(db: MovieDatabase): LocalDataSource = RoomDataSource(db)
}