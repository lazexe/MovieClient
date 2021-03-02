package betterme.moviesclient.framework.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE cached = 1")
    fun getMoviesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: Int): Movie

    @Query("SELECT * FROM movie WHERE favourite = 1")
    fun getFavouritesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE favourite = 1")
    fun getFavourites(): List<Movie>

    @Query("UPDATE movie SET favourite = 1 WHERE id = :movieId")
    suspend fun setFavourite(movieId: Int)

    @Query("UPDATE movie SET favourite = 0 WHERE id = :movieId")
    suspend fun removeFavourite(movieId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("UPDATE movie SET cached = 0")
    suspend fun clearCachedMarks()

    @Query("DELETE FROM movie WHERE cached = 0 AND favourite = 0")
    suspend fun deleteCachedMovies()

    @Transaction
    suspend fun cacheMovies(movies: List<Movie>) {
        clearCachedMarks()
        deleteCachedMovies()
        insert(movies)
    }
}