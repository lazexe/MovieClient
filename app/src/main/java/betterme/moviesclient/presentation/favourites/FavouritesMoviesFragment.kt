package betterme.moviesclient.presentation.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import betterme.moviesclient.MovieApplication
import betterme.moviesclient.databinding.FavouritesMoviesFragmentBinding
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.presentation.base.MovieListFragment

class FavouritesMoviesFragment : MovieListFragment() {

    private var _binding: FavouritesMoviesFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavouritesMoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FavouritesMoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getRecyclerView() = binding.favouriteMoviesRecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as MovieApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FavouritesMoviesViewModel::class.java)

        viewModel.favouritesMoviesLiveData.observe(this) {
            it?.let { showMovies(it) }
        }
    }

    private fun showMovies(movies: List<Movie>) {
        adapter.updateItems(movies)
    }

    override fun onFavouriteClicked(movie: Movie) {
        viewModel.removeFromFavourites(movie)
    }
}