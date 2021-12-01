package com.example.movies_v8_1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_v8_1.MoviesAdapter
import com.example.movies_v8_1.data.MoviesData
import com.example.movies_v8_1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val adapter: MoviesAdapter = MoviesAdapter()
    private val moviesData = MoviesData()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.rvMain

        recyclerView.adapter = adapter
        adapter.setData(moviesData.list)
        adapter.listener = object: MoviesAdapter.OnButtonClickListener{
            override fun onButtonClick(name: String) {
                // выделить другим цветом название выбранного фильма
                moviesData.setButtonPressed(name)
                adapter.notifyDataSetChanged()

                //val amount = amountTv.text.toString().toInt()
                val action = HomeFragmentDirections.actionNavHomeToMovieDetailsFragment(name)
                findNavController().navigate(action)
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}