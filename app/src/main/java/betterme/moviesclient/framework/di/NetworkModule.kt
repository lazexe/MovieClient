package betterme.moviesclient.framework.di

import betterme.moviesclient.BuildConfig
import betterme.moviesclient.data.abs.RemoteDataSource
import betterme.moviesclient.framework.retrofit.MovieDbApi
import betterme.moviesclient.framework.retrofit.MovieDbDataSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
@Module
class NetworkModule {

    private val timeout = 30L

    @Provides
    @Singleton
    fun provideHttpInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .writeTimeout(timeout, TimeUnit.SECONDS)
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): MovieDbApi {
        return Retrofit.Builder().baseUrl(BuildConfig.MOVIE_DB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build().create(MovieDbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: MovieDbApi): RemoteDataSource = MovieDbDataSource(api)
}