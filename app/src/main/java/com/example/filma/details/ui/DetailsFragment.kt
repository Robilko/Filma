package com.example.filma.details.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.filma.MOVIE_ITEM_ID
import com.example.filma.R
import com.example.filma._core.ui.adapter.PersonListAdapter
import com.example.filma._core.ui.model.DetailsMovie
import com.example.filma._core.ui.model.state.ItemViewState
import com.example.filma.databinding.FragmentDetailsBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private val viewModel by viewModel<DetailsViewModel>()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val actorListAdapter: PersonListAdapter = PersonListAdapter()
    private val directorsListAdapter: PersonListAdapter = PersonListAdapter()
    private lateinit var movieId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
        initData()
    }

    private fun initViewModel() {
        viewModel.viewState.onEach { renderState(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initView() = with(binding) {
        actorsLayout.actorsRecycler.adapter = actorListAdapter
        directorsLayout.directorsRecycler.adapter = directorsListAdapter
    }

    private fun initData() {
        movieId = arguments?.getString(MOVIE_ITEM_ID)!!
        viewModel.getMovieDetails(movieId = movieId)
    }

    private fun renderState(state: ItemViewState) = when (state) {
        is ItemViewState.Loading -> {
            enableProgress(state = true)
            enableEmptyState(state = false)
            enableContent(state = false)
        }
        is ItemViewState.Error -> {
            enableEmptyState(state = true)
            enableProgress(state = false)
            enableContent(state = false)
            showToastMessage(message = state.message ?: getString(R.string.unknown_error))
        }
        is ItemViewState.Data -> {
            enableProgress(state = false)
            enableContent(state = true)
            enableEmptyState(state = false)
            setData(data = state.data)
        }
    }

    private fun setData(data: DetailsMovie) {
        with(binding.movieParametersLayout) {
            Glide.with(detailsMovieImage.context)
                .load(data.posterUrl)
                .error(R.drawable.ic_image_not_supported)
                .placeholder(R.drawable.ic_movie_default_image)
                .into(detailsMovieImage)
            detailsTitle.text = data.name
            detailsMovieYearContent.text = data.year
            detailsMovieTypeContent.text = data.type
            detailsMovieGenreContent.text = data.genres
            detailsMovieCountryContent.text = data.countries
            detailsMovieRuntimeContent.text = data.movieLength
            detailsImdbRatingContent.text = data.imDbRating
            detailsKinopoiskRatingContent.text = data.kinopoiskRating
        }

        with(binding.plotLayout) {
            detailsMoviePlotText.text = data.description
        }

        directorsListAdapter.submitList(data.directors)

        actorListAdapter.submitList(data.actors)
    }

    private fun showToastMessage(message: String) {
        showWarningDialog(message)
    }

    private fun enableContent(state: Boolean) {
        binding.detailsContent.isVisible = state
    }

    private fun enableProgress(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun enableEmptyState(state: Boolean) {
        binding.detailsFragmentEmptyData.isVisible = state
    }

    private fun showWarningDialog(errorMessage: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(errorMessage)
            .setPositiveButton(R.string.retry) { _, _ -> viewModel.getMovieDetails(movieId = movieId) }
            .setNegativeButton(R.string.close) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}