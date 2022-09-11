package com.example.filma.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.filma.MOVIE_ITEM_ID
import com.example.filma.R
import com.example.filma._core.ui.adapter.MovieListAdapter
import com.example.filma._core.ui.adapter.RecyclerItemListener
import com.example.filma._core.ui.model.Movie
import com.example.filma.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val viewModel by viewModel<MainViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val recyclerItemListener = object : RecyclerItemListener {
        override fun onItemClick(itemMovie: Movie) {
            startDetailsFragment(itemMovie.id)
        }
    }

    private val movieListAdapter: MovieListAdapter =
        MovieListAdapter(listener = recyclerItemListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupView()
    }


    private fun setupList() = with(binding) {
        mainRecycler.adapter = movieListAdapter
    }

    private fun setupView() {
        binding.fab.setOnClickListener { startSearchFragment() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieListAdapter.addLoadStateListener {
                    renderState(it.refresh)
                }

                viewModel.items.collect {
                    movieListAdapter.submitData(it)
                }
            }
        }


    }

    private fun renderState(state: LoadState) = when (state) {
        is LoadState.Loading -> {
            enableProgress(state = true)
            enableEmptyState(state = false)
            enableContent(state = false)
        }
        is LoadState.Error -> {
            enableEmptyState(state = true)
            enableProgress(state = false)
            enableContent(state = false)
            showToastMessage(
                message = state.error.localizedMessage ?: getString(R.string.unknown_error)
            )
        }
        else -> {
            enableProgress(state = false)
            enableContent(state = true)
            enableEmptyState(state = false)
        }
    }

    private fun showToastMessage(message: String) {
        showWarningDialog(message)
    }

    private fun enableContent(state: Boolean) {
        binding.mainRecycler.isVisible = state
    }

    private fun enableProgress(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun enableEmptyState(state: Boolean) {
        binding.mainFragmentEmptyData.isVisible = state
    }

    private fun showWarningDialog(errorMessage: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(errorMessage)
            .setPositiveButton(getString(R.string.retry)) { _, _ -> movieListAdapter.retry() }
            .setNegativeButton(R.string.close) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun startDetailsFragment(id: String) {
        val bundle = bundleOf(MOVIE_ITEM_ID to id)
        findNavController().navigate(R.id.action_MainFragment_to_DetailsFragment, bundle)
    }

    private fun startSearchFragment() {
        findNavController().navigate(R.id.action_MainFragment_to_SearchFragment)
    }
}