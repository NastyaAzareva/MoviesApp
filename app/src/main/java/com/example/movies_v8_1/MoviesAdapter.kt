package com.example.movies_v8_1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_v8_1.data.Movie


class MoviesAdapter(): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val movies: ArrayList<Movie> = ArrayList<Movie>()
    var listener: OnButtonClickListener? = null

    class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var movieName: TextView = itemView.findViewById(R.id.movieName)
        var movieDescr: TextView = itemView.findViewById(R.id.movieDescr)
        var moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)
        var detailsButton: Button = itemView.findViewById(R.id.buttonDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.movieName.text = movies[holder.adapterPosition].name
        if(movies[holder.adapterPosition].isButtonPressed){
            holder.movieName.setTextColor(Color.GREEN)
        }
        else{
            holder.movieName.setTextColor(Color.BLACK)
        }

        holder.movieDescr.text = movies[holder.adapterPosition].description
        holder.moviePoster.setBackgroundResource( movies[holder.adapterPosition].poster)

        holder.detailsButton.setOnClickListener {
            listener?.onButtonClick(movies[holder.adapterPosition].name)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setData(list: List<Movie>){
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    interface OnButtonClickListener{
        fun onButtonClick(name: String)
    }
}

