package betterme.moviesclient.presentation.resources

import android.content.res.Resources

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */
class AppResourceProvider(private val resources: Resources)  : ResourcesProvider {

    override fun getString(resId: Int): String = resources.getString(resId)

    override fun getString(resId: Int, vararg args: Any): String = resources.getString(resId, *args)
}