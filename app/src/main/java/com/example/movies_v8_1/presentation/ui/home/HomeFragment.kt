package com.example.movies_v8_1.presentation.ui.home

import android.os.Bundle
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
                        adapter.setData(t.data)
                        setSuccessStateView()
                    }
                    is MovieListState.Loading -> {
                        setLoadingStateView()
                    }
                    is MovieListState.Error -> {
                        setErrorStateView()
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

    fun setSuccessStateView(){
        with(binding){
            progressBar?.visibility = View.INVISIBLE
            rvMain.visibility = View.VISIBLE
            errorTextView?.visibility = View.INVISIBLE
            refreshButton?.visibility = View.INVISIBLE
            pullToRefresh?.isRefreshing = false
        }
    }

    fun setLoadingStateView(){
        with(binding){
            rvMain.visibility = View.INVISIBLE
            progressBar?.visibility = View.VISIBLE
            errorTextView?.visibility = View.INVISIBLE
            refreshButton?.visibility = View.INVISIBLE
        }
    }
    fun setErrorStateView(){
        with(binding) {
            rvMain.visibility = View.INVISIBLE
            progressBar?.visibility = View.INVISIBLE
            errorTextView?.visibility = View.VISIBLE
            refreshButton?.visibility = View.VISIBLE
            pullToRefresh?.isRefreshing = false
        }
    }
}