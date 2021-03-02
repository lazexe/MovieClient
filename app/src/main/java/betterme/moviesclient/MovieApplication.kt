package betterme.moviesclient

import android.app.Application
import betterme.moviesclient.framework.di.ApplicationComponent
import betterme.moviesclient.framework.di.ApplicationModule
import betterme.moviesclient.framework.di.DaggerApplicationComponent
import betterme.moviesclient.framework.timber.DebugTree
import timber.log.Timber

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
class MovieApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
    }
}