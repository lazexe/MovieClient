package betterme.moviesclient.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import betterme.moviesclient.R
import betterme.moviesclient.domain.Movie
import betterme.moviesclient.presentation.adapter.MovieRecyclerAdapter
import betterme.moviesclient.presentation.ViewModelFactory
import betterme.moviesclient.presentation.resources.ResourcesProvider
import javax.inject.Inject


/**
 * Created by Maksym Lazarenko on 2/26/21.
 * Skype: lazexe
 */
abstract class MovieListFragment : Fragment(), MovieRecyclerAdapter.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    val adapter = MovieRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onClickListener = this

        val layoutManager = LinearLayoutManager(context)
        getRecyclerView().layoutManager = layoutManager
        getRecyclerView().adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.onClickListener = null
    }

    abstract fun getRecyclerView(): RecyclerView

    override fun onShareClicked(movie: Movie) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, resourcesProvider.getString(R.string.share_movie_subject))
            putExtra(Intent.EXTRA_TEXT, resourcesProvider.getString(R.string.share_movie_text, movie.title, movie.overview))
            type = "text/plain"
        }
        startActivity(intent)
    }
}