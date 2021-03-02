package betterme.moviesclient.presentation.movies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import betterme.moviesclient.MovieApplication
import betterme.moviesclient.databinding.MoviesFragmentBinding
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.presentation.base.MovieListFragment
import betterme.moviesclient.presentation.NetworkStatus

class MoviesFragment : MovieListFragment() {

    private var _binding: MoviesFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getRecyclerView() = binding.moviesRecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as MovieApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)

        binding.moviesSwipeRefresh.setOnRefreshListener {
            viewModel.refreshMovies()
        }

        viewModel.moviesLiveData.observe(this) {
            it?.let { showMovies(it) }
        }

        viewModel.networkStatus.observe(this) {
            it?.let { handleNetworkStatus(it) }
        }
    }

    private fun showMovies(movies: List<Movie>) {
        adapter.updateItems(movies)
    }

    private fun handleNetworkStatus(networkStatus: NetworkStatus) = when (networkStatus) {
        is NetworkStatus.Failed -> {
            binding.moviesSwipeRefresh.isRefreshing = false
            Toast.makeText(context, networkStatus.errorMessage, Toast.LENGTH_SHORT).show()
        }
        is NetworkStatus.InProgress -> {
            binding.moviesSwipeRefresh.isRefreshing = true
        }
        is NetworkStatus.Success -> {
            binding.moviesSwipeRefresh.isRefreshing = false
        }
    }

    override fun onFavouriteClicked(movie: Movie) {
        viewModel.handleFavouriteClicked(movie)
    }
}