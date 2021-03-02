package betterme.moviesclient.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import betterme.moviesclient.domain.Movie

/**
 * Created by Maksym Lazarenko on 3/1/21.
 * Skype: lazexe
 */
class MoviesDiffCallback(
    private val oldList: List<Movie>,
    private val newList: List<Movie>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}