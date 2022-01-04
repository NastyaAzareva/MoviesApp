package com.example.movies_v8_1.presentation.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movies_v8_1.R
import com.example.movies_v8_1.databinding.FragmentMovieDetailsBinding
import com.example.movies_v8_1.domain.model.MovieDetailsModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentMovieDetailsBinding
    private val movieDetailsVM: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsVM.state.observe(viewLifecycleOwner, object : Observer<MovieDetailState> {
            override fun onChanged(t: MovieDetailState?) {
                t?.let {
                    if (it.error != "") {
                        binding.mainAppbar.visibility = View.GONE
                        binding.nestedScrollView?.visibility = View.GONE
                        binding.fabDetails.visibility = View.GONE

                        binding.progressBar?.visibility = View.GONE

                        binding.errorTextView?.visibility = View.VISIBLE
                        binding.refreshButton?.visibility = View.VISIBLE
                    }
                    if (it.isLoading) {
                        binding.mainAppbar.visibility = View.GONE
                        binding.nestedScrollView?.visibility = View.GONE
                        binding.fabDetails.visibility = View.GONE

                        binding.progressBar?.visibility = View.VISIBLE

                        binding.errorTextView?.visibility = View.GONE
                        binding.refreshButton?.visibility = View.GONE
                    }
                    if (it.movie != null) {
                        setMovieDetailsUIData(it.movie)

                        binding.mainAppbar.visibility = View.VISIBLE
                        binding.nestedScrollView?.visibility = View.VISIBLE
                        binding.fabDetails.visibility = View.VISIBLE

                        binding.progressBar?.visibility = View.GONE

                        binding.errorTextView?.visibility = View.GONE
                        binding.refreshButton?.visibility = View.GONE
                    }
                }
            }
        })
        movieDetailsVM.getMovies(args.movieId)
    }

    private fun setMovieDetailsUIData(movie: MovieDetailsModel) {
        binding.movieDescription.text = movie.description
        Glide.with(binding.mainBackdrop)
            .load(movie.posterURL) // image url
            //.placeholder(R.drawable.ic_baseline_texture_24) // any placeholder to load at start To Do
            .error(R.drawable.ic_baseline_image_24)  // any image in case of error
            .override(200, 200) // resizing
            .centerCrop()
            .into(binding.mainBackdrop);  // imageview object
    }
}