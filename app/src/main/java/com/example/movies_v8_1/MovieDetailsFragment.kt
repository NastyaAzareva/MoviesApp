package com.example.movies_v8_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movies_v8_1.data.MoviesData
import com.example.movies_v8_1.databinding.FragmentMovieDetailsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieDetailsFragment : Fragment() {
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        setMovieDetailsUI(args.movieName)

        binding.fabDetails.setOnClickListener {
            args.movieName?.let { it1 -> MoviesData.setFavourite(it1) }
        }
        return binding.root
    }

    private fun setMovieDetailsUI(movieName: String?){
        if (movieName != "" && movieName != null){
            binding.movieDescription.text = MoviesData.getDescriptionByName(movieName)

            if(MoviesData.getPosterByName(movieName) != 0) {
                binding.mainBackdrop.setImageResource(MoviesData.getPosterByName(movieName))
            }
        }
    }
}