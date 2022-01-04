package com.example.movies_v8_1.presentation.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies_v8_1.R
import com.example.movies_v8_1.databinding.ItemMovieBinding
import com.example.movies_v8_1.domain.model.MovieModel

class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val movies = ArrayList<MovieModel>()
    private var highlightedId: Long? = null
    var listener: Listener? = null

    class MoviesViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.binding.apply {
            movieName.text = movies[holder.adapterPosition].nameRu

            if (movies[holder.adapterPosition].kinopoiskID == highlightedId) {
                movieName.setTextColor(Color.GREEN)
            } else {
                movieName.setTextColor(Color.BLACK)
            }

            //To Do убрать когда переверстаю item для home и favourites
            movieDescr.text = "описание описание описание"
           // moviePoster.setImageResource()
                //movies[holder.adapterPosition].posterURLPreview)

            Glide.with(holder.itemView)
                .load(movies[holder.adapterPosition].posterURLPreview) // image url
                .placeholder(R.drawable.ic_baseline_texture_24) // any placeholder to load at start
                .error(R.drawable.ic_baseline_image_24)  // any image in case of error
                .override(200, 200) // resizing
                .centerCrop()
                .into(moviePoster);  // imageview object

            //To Do избавиться от кнопки чтобы можно было кликать по самому айтему
            buttonDetails.setOnClickListener {
                listener?.onButtonClick(movies[holder.adapterPosition].kinopoiskID)
            }

            root.setOnLongClickListener {
                listener?.onLongClick(movies[holder.adapterPosition].kinopoiskID)
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount() = movies.size

    fun setHighlightedId(id: Long){
        highlightedId = id
    }

    fun setData(list: List<MovieModel>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onButtonClick(movieId: Long)
        fun onLongClick(movieId: Long)
    }
}