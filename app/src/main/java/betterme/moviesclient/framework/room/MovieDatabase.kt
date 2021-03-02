package betterme.moviesclient.framework.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */
@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}