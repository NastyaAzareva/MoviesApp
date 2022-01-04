package com.example.movies_v8_1.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.viewbinding.ViewBinding
import com.example.movies_v8_1.presentation.ui.MoviesAdapter
import com.example.movies_v8_1.presentation.ui.NotUglyItemDecoration
import com.example.movies_v8_1.R
import com.example.movies_v8_1.common.toFavouriteMoviesEntity
import com.example.movies_v8_1.databinding.FragmentHomeBinding
import com.example.movies_v8_1.presentation.ui.favourites.FavouritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeVM: HomeViewModel by viewModels()
    private val favouritesVM: FavouritesViewModel by viewModels()
    private val adapter: MoviesAdapter = MoviesAdapter()
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.rvMain
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        homeVM.state.observe(viewLifecycleOwner, object : Observer<MovieListState> {
            override fun onChanged(t: MovieListState?) {
                when (t) {
                    is MovieListState.Success -> {
                        adapter.setData(homeVM.list)
                        binding.progressBar?.visibility = View.INVISIBLE
                        binding.rvMain.visibility = View.VISIBLE
                        binding.errorTextView?.visibility = View.INVISIBLE
                        binding.refreshButton?.visibility = View.INVISIBLE
                        binding.pullToRefresh?.isRefreshing = false
                    }
                    is MovieListState.Loading -> {
                        Log.d("qwerty", "Loading...")
                        binding.rvMain.visibility = View.INVISIBLE
                        binding.progressBar?.visibility = View.VISIBLE
                        binding.errorTextView?.visibility = View.INVISIBLE
                        binding.refreshButton?.visibility = View.INVISIBLE
                    }
                    is MovieListState.Error -> {
                        Log.d("qwerty ", t.message)
                        binding.rvMain.visibility = View.INVISIBLE
                        binding.progressBar?.visibility = View.INVISIBLE
                        binding.errorTextView?.visibility = View.VISIBLE
                        binding.refreshButton?.visibility = View.VISIBLE
                        binding.pullToRefresh?.isRefreshing = false
                    }
                }
            }
        })

        recyclerView.addItemDecoration(NotUglyItemDecoration())

        recyclerView.addOnScrollListener(object : OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy>10) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    if ( (visibleItemCount+pastVisibleItems) >= totalItemCount) {
                        homeVM.loadMoreMovies()
                    }
                }

            }
        })

        binding.refreshButton?.setOnClickListener {
            homeVM.getMovies()
        }

        binding.pullToRefresh?.setOnRefreshListener {
            homeVM.getMovies()
        }

        adapter.listener = object : MoviesAdapter.Listener {
            override fun onButtonClick(movieId: Long) {
                adapter.setHighlightedId(movieId)

                findNavController().navigate(
                    HomeFragmentDirections.actionNavHomeToMovieDetailsFragment(
                        movieId
                    )
                )
            }

            //Добавляем фильм в Favourites
            override fun onLongClick(movieId: Long) {
                homeVM.getMovieByID(movieId)?.let {
                    favouritesVM.addMovieToFavourites(it.toFavouriteMoviesEntity())
                }

                Toast.makeText(
                    context,
                    "$movieId " + requireContext().getString(R.string.added_to_favourites_message),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}