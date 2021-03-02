package betterme.moviesclient.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import betterme.moviesclient.R
import betterme.moviesclient.databinding.MovieItemBinding
import betterme.moviesclient.domain.Movie
import com.bumptech.glide.Glide
import timber.log.Timber

/**
 * Created by Maksym Lazarenko on 2/25/21.
 * Skype: lazexe
 */
class MovieRecyclerAdapter :
    RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {

    private val movies = mutableListOf<Movie>()

    var onClickListener: OnClickListener? = null

    fun updateItems(movies: List<Movie>) {
        val diffCallback = MoviesDiffCallback(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.movies.clear()
        this.movies.addAll(movies)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

        holder.favouriteButton.setOnClickListener {
            onClickListener?.onFavouriteClicked(movie)
        }

        holder.shareButton.setOnClickListener {
            onClickListener?.onShareClicked(movie)
        }
    }

    override fun getItemCount() = movies.size

    class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val favouriteButton = binding.favouriteButton
        val shareButton = binding.shareButton

        fun bind(movie: Movie) {
            binding.movieTitleTextView.text = movie.title
            binding.movieOverviewTextView.text = movie.overview

            val favouriteTextResourceId = if (movie.favourite)
                R.string.movie_item_remove_favourites
            else
                R.string.movie_item_add_favourites
            val text = itemView.context.getString(favouriteTextResourceId)
            binding.favouriteButton.text = text

            Glide.with(itemView)
                .load(movie.posterUrl)
                .centerCrop()
                .into(binding.movieIconImageView)
        }
    }

    interface OnClickListener {
        fun onFavouriteClicked(movie: Movie)
        fun onShareClicked(movie: Movie)
    }
}

