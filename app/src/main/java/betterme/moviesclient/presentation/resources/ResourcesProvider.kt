package betterme.moviesclient.presentation.resources

import androidx.annotation.StringRes

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */
interface ResourcesProvider {

    fun getString(@StringRes resId: Int): String

    fun getString(resId: Int, vararg args: Any): String
}