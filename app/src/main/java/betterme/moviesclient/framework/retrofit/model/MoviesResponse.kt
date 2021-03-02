package betterme.moviesclient.framework.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Maksym Lazarenko on 2/23/21.
 * Skype: lazexe
 */
data class MoviesResponse(
    @SerializedName("page")             @Expose val page: Int,
    @SerializedName("results")          @Expose val results: List<Movie>,
    @SerializedName("total_pages")      @Expose val totalPages: Int,
    @SerializedName("total_results")    @Expose val totalResults: Int,
)