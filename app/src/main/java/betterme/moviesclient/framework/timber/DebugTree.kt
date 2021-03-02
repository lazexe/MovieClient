package betterme.moviesclient.framework.timber

import timber.log.Timber

/**
 * Created by Maksym Lazarenko on 2/25/21.
 * Skype: lazexe
 */
class DebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String {
        return "[TIMBER] -> ${super.createStackElementTag(element)}.${element.methodName}() at line[${element.lineNumber}]"
    }
}