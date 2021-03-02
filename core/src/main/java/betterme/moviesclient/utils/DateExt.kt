package betterme.moviesclient.utils

import java.util.*

/**
 * Created by Maksym Lazarenko on 3/1/21.
 * Skype: lazexe
 */

fun Date.add(field: Int, amount: Int): Date {
    Calendar.getInstance().apply {
        time = this@add
        add(field, amount)
        return time
    }
}

fun Date.removeTwoWeeks() = this.add(Calendar.DATE, -14)