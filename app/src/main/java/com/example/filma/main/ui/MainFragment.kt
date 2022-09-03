package com.example.filma.main.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.filma.R
import com.example.filma.TAG
import com.example.filma._core.ui.adapter.MovieListAdapter
import com.example.filma._core.ui.adapter.RecyclerItemListener
import com.example.filma._core.ui.model.ListViewState
import com.example.filma._core.ui.model.Movie
import com.example.filma.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val viewModel by viewModel<MainViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val recyclerItemListener = object : RecyclerItemListener {
        override fun onItemClick(itemMovie: Movie) {
            //TODO("Not yet implemented")
        }
    }

    private val movieListAdapter: MovieListAdapter =
        MovieListAdapter(listener = recyclerItemListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(
            TAG,
            "onCreateView() called with: inflater = $inflater, container = $container, savedInstanceState = $savedInstanceState"
        )
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(
            TAG,
            "onViewCreated() called with: view = $view, savedInstanceState = $savedInstanceState"
        )
        initViewModel()
        initView()
        initData()

//        binding.buttonFirst.setOnClickListener {
//            val itemId = "Some argument"
//            val bundle = bundleOf("Some argument" to itemId)
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
//        }
    }

    private fun initViewModel() {
        Log.d(TAG, "initViewModel() called")
        viewModel.viewState.onEach { renderState(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initView() = with(binding) {
        Log.d(TAG, "initView() called")
        mainRecycler.adapter = movieListAdapter
    }

    private fun initData() {
        Log.d(TAG, "initData() called")
        viewModel.getData()
    }

    private fun renderState(state: ListViewState) = when (state) {
        is ListViewState.Loading -> {
            enableProgress(state = true)
            enableEmptyState(state = false)
            enableContent(state = false)
        }
        is ListViewState.Error -> {
            enableEmptyState(state = true)
            enableProgress(state = false)
            enableContent(state = false)
            showToastMessage(message = state.message ?: getString(R.string.unknown_error))
        }
        is ListViewState.Data -> {
            enableProgress(state = false)
            enableContent(state = true)
            enableEmptyState(state = false)
            setDataToAdapter(data = state.data)
        }
    }

    private fun setDataToAdapter(data: List<Movie>) {
        movieListAdapter.submitList(data)
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
            .setPositiveButton(R.string.retry) { _, _ -> viewModel.getData() }
            .setNegativeButton(R.string.close) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}