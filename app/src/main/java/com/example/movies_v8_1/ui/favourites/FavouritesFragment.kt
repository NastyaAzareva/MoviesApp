package com.example.movies_v8_1.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_v8_1.MoviesAdapter
import com.example.movies_v8_1.R
import com.example.movies_v8_1.data.MoviesData
import com.example.movies_v8_1.databinding.FragmentFavouritesBinding
import com.example.movies_v8_1.ui.home.HomeFragmentDirections

class FavouritesFragment : Fragment() {
    private lateinit var favouritesViewModel: FavouritesViewModel
    private val adapter: MoviesAdapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel = ViewModelProvider(this)[FavouritesViewModel::class.java]

        val binding = FragmentFavouritesBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = binding.rvMain
        recyclerView.adapter = adapter
        adapter.setData(MoviesData.getFavouritesList())

        adapter.listener = object : MoviesAdapter.Listener {
            override fun onButtonClick(name: String) {
                // выделить другим цветом название выбранного фильма
                MoviesData.setButtonPressed(name)
                adapter.setData(MoviesData.getFavouritesList())

                findNavController().navigate(
                    FavouritesFragmentDirections.actionNavFavouritesToMovieDetailsFragment(
                        name
                    )
                )
            }

            override fun onLongClick(name: String) {
                MoviesData.removeFromFavourites(name)
                Toast.makeText(context, "$name " + requireContext().getString(R.string.removed_from_favourites_message), Toast.LENGTH_LONG).show()
                adapter.setData(MoviesData.getFavouritesList())


            }
        }

        return binding.root
    }

}