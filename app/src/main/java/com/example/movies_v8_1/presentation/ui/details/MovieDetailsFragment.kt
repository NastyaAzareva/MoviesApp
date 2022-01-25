package com.example.movies_v8_1.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movies_v8_1.R
import com.example.movies_v8_1.common.toFavouriteMoviesEntity
import com.example.movies_v8_1.common.toSeeLaterMoviesEntity
import com.example.movies_v8_1.databinding.FragmentMovieDetailsBinding
import com.example.movies_v8_1.domain.model.MovieDetailsModel
import com.example.movies_v8_1.presentation.ui.favourites.FavouritesViewModel
import com.example.movies_v8_1.presentation.ui.later.LaterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentMovieDetailsBinding
    private val movieDetailsVM: MovieDetailViewModel by viewModels()

    private var currentMovie: MovieDetailsModel? = null

    private val favouritesVM: FavouritesViewModel by viewModels()
    private val seeLaterVM: LaterViewModel by viewModels()

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
                        setErrorStateView()
                    }
                    if (it.isLoading) {
                        setLoadingStateView()
                    }
                    if (it.movie != null) {
                        setMovieDetailsUIData(it.movie)
                        currentMovie = it.movie

                        setSuccessStateView()
                    }
                }
            }
        })
        movieDetailsVM.getMovies(args.movieId)

        binding.fabAddToFavourite.setOnClickListener(
            object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    currentMovie?.let { favouritesVM.addMovieToFavourites(it.toFavouriteMoviesEntity()) }
                }
            })

        binding.fabAddToSeeLater?.setOnClickListener(
            object: View.OnClickListener{
                override fun onClick(p0: View?) {
                    currentMovie?.let { seeLaterVM.addToSeeLaterList(it.toSeeLaterMoviesEntity()) }
                }
            }
        )
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

    fun setSuccessStateView(){
        with(binding){
            mainAppbar.visibility = View.VISIBLE
            nestedScrollView?.visibility = View.VISIBLE
            fabAddToFavourite.visibility = View.VISIBLE
            progressBar?.visibility = View.GONE
            errorTextView?.visibility = View.GONE
            refreshButton?.visibility = View.GONE
        }
    }

    fun setLoadingStateView(){
        with(binding){
            mainAppbar.visibility = View.GONE
            nestedScrollView?.visibility = View.GONE
            fabAddToFavourite.visibility = View.GONE
            progressBar?.visibility = View.VISIBLE
            errorTextView?.visibility = View.GONE
            refreshButton?.visibility = View.GONE
        }
    }
    fun setErrorStateView(){
        with(binding) {
            mainAppbar.visibility = View.GONE
            nestedScrollView?.visibility = View.GONE
            fabAddToFavourite.visibility = View.GONE
            progressBar?.visibility = View.GONE
            errorTextView?.visibility = View.VISIBLE
            refreshButton?.visibility = View.VISIBLE
        }
    }
}