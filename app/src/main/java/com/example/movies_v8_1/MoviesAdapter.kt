package com.example.movies_v8_1

import android.graphics.Color
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_v8_1.data.Movie
import com.example.movies_v8_1.databinding.ItemMovieBinding


class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val movies: ArrayList<Movie> = ArrayList<Movie>()
    var listener: Listener? = null

    class MoviesViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.binding.apply {
            movieName.text = movies[holder.adapterPosition].name
            if (movies[holder.adapterPosition].isButtonPressed) {
                movieName.setTextColor(Color.GREEN)
            } else {
                movieName.setTextColor(Color.BLACK)
            }

            movieDescr.text = movies[holder.adapterPosition].description
            moviePoster.setImageResource(movies[holder.adapterPosition].poster)

            buttonDetails.setOnClickListener {
                listener?.onButtonClick(movies[holder.adapterPosition].name)
            }

            root.setOnLongClickListener {
                listener?.onLongClick(movies[holder.adapterPosition].name)
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount() = movies.size

    fun setData(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onButtonClick(name: String)
        fun onLongClick(name: String)
    }
}

class NotUglyItemDecoration() : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top += 20
        outRect.right += 20
        outRect.left += 20
        outRect.bottom += 20
    }

}



