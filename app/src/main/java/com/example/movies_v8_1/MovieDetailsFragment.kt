package com.example.movies_v8_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movies_v8_1.data.MoviesData

class MovieDetailsFragment : Fragment() {
    private val moviesData = MoviesData()
    private val args: MovieDetailsFragmentArgs by navArgs()
    lateinit var img: ImageView
    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_movie_details, container, false)
        img = v.findViewById(R.id.main_backdrop)
        tv = v.findViewById(R.id.movieDescription)

        setMovieDetailsUI(args.movieName)
        return v
    }

    private fun setMovieDetailsUI(movieName: String?){
        if (movieName != "" && movieName != null){
            tv.text = moviesData.getDescriptionByName(movieName)

            if(moviesData.getPosterByName(movieName) != 0) {
                img.setImageResource(moviesData.getPosterByName(movieName))
            }
        }
    }
}