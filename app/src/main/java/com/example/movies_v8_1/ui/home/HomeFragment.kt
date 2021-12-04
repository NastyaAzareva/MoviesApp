package com.example.movies_v8_1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_v8_1.MoviesAdapter
import com.example.movies_v8_1.NotUglyItemDecoration
import com.example.movies_v8_1.R
import com.example.movies_v8_1.data.MoviesData
import com.example.movies_v8_1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val adapter: MoviesAdapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val recyclerView: RecyclerView = binding.rvMain

        recyclerView.adapter = adapter
        adapter.setData(MoviesData.list)

        recyclerView.addItemDecoration(NotUglyItemDecoration())

        adapter.listener = object : MoviesAdapter.Listener {
            override fun onButtonClick(name: String) {
                MoviesData.setButtonPressed(name)
                adapter.setData(MoviesData.list)

                findNavController().navigate(
                    HomeFragmentDirections.actionNavHomeToMovieDetailsFragment(
                        name
                    )
                )
            }

            override fun onLongClick(name: String) {
                MoviesData.setFavourite(name)
                Toast.makeText(context, "$name " + requireContext().getString(R.string.added_to_favourites_message), Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }
}