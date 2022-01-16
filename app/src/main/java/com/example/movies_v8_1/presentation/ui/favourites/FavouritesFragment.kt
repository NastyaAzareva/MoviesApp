package com.example.movies_v8_1.presentation.ui.favourites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_v8_1.presentation.ui.MoviesAdapter
import com.example.movies_v8_1.databinding.FragmentFavouritesBinding
import com.example.movies_v8_1.databinding.FragmentHomeBinding
import com.example.movies_v8_1.presentation.ui.home.MovieListState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private  val favouritesVM: FavouritesViewModel by viewModels()
    private val adapter: MoviesAdapter = MoviesAdapter()
    lateinit var binding: FragmentFavouritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.rvMain

        recyclerView.adapter = adapter

        favouritesVM.state.observe(viewLifecycleOwner, object : Observer<MovieListState> {
            override fun onChanged(t: MovieListState?) {
                when (t) {
                    is MovieListState.Success -> {
                        adapter.setData(t.data)
                        binding.errorTextView?.visibility = View.GONE
                        binding.refreshButton?.visibility = View.GONE

                        binding.progressBar?.visibility = View.GONE

                        binding.rvMain.visibility = View.VISIBLE
                    }
                    is MovieListState.Loading -> {
                        binding.errorTextView?.visibility = View.GONE
                        binding.refreshButton?.visibility = View.GONE

                        binding.progressBar?.visibility = View.VISIBLE

                        binding.rvMain.visibility = View.GONE
                    }
                    is MovieListState.Error -> {
                        binding.errorTextView?.visibility = View.VISIBLE
                        binding.refreshButton?.visibility = View.VISIBLE

                        binding.progressBar?.visibility = View.GONE

                        binding.rvMain.visibility = View.GONE
                    }
                }
            }
        })

        adapter.listener = object : MoviesAdapter.Listener {
            override fun onButtonClick(movieId: Long) {
                // выделить другим цветом название выбранного фильма
                adapter.setHighlightedId(movieId)

                findNavController().navigate(
                    FavouritesFragmentDirections.actionNavFavouritesToMovieDetailsFragment(
                        movieId
                    )
                )
            }

            override fun onLongClick(movieId: Long) {
                Log.d("qwerty ", "Favourites - long click")
            }
        }

    }
}