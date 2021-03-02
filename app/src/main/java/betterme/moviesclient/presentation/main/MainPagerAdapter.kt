package betterme.moviesclient.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import betterme.moviesclient.R
import betterme.moviesclient.presentation.favourites.FavouritesMoviesFragment
import betterme.moviesclient.presentation.movies.MoviesFragment
import betterme.moviesclient.presentation.resources.ResourcesProvider
import betterme.moviesclient.presentation.stub.StubFragment

/**
 * Created by Maksym Lazarenko on 2/24/21.
 * Skype: lazexe
 */
class MainPagerAdapter(
    private val resourcesProvider: ResourcesProvider,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val pageCount = 2

    override fun getCount() = pageCount

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment()
            1 -> FavouritesMoviesFragment()
            else -> StubFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> resourcesProvider.getString(R.string.tab_films)
            1 -> resourcesProvider.getString(R.string.tab_favourites)
            else -> null
        }
    }
}