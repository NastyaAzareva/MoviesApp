package com.example.movies_v8_1.presentation.ui.later

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_v8_1.databinding.FragmentLaterBinding
import com.example.movies_v8_1.presentation.ui.MoviesAdapter
import com.example.movies_v8_1.presentation.ui.home.MovieListState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaterFragment : Fragment() {
    lateinit var binding: FragmentLaterBinding
    private val adapter: MoviesAdapter = MoviesAdapter()
    private val seeLaterVM: LaterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.rvMain

        recyclerView.adapter = adapter

        seeLaterVM.state.observe(viewLifecycleOwner, object : Observer<MovieListState> {
            override fun onChanged(t: MovieListState?) {
                when (t) {
                    is MovieListState.Success -> {
                        adapter.setData(t.data)
                        binding.errorTextView.visibility = View.GONE
                        binding.refreshButton.visibility = View.GONE

                        binding.progressBar.visibility = View.GONE

                        binding.rvMain.visibility = View.VISIBLE
                    }
                    is MovieListState.Loading -> {
                        binding.errorTextView.visibility = View.GONE
                        binding.refreshButton.visibility = View.GONE

                        binding.progressBar.visibility = View.VISIBLE

                        binding.rvMain.visibility = View.GONE
                    }
                    is MovieListState.Error -> {
                        binding.errorTextView.visibility = View.VISIBLE
                        binding.refreshButton.visibility = View.VISIBLE

                        binding.progressBar.visibility = View.GONE

                        binding.rvMain.visibility = View.GONE
                    }
                }
            }
        })
    }
}