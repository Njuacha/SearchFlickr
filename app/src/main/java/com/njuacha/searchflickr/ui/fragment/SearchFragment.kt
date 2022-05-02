package com.njuacha.searchflickr.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.njuacha.searchflickr.R
import com.njuacha.searchflickr.ui.adapter.SearchAdapter
import com.njuacha.searchflickr.databinding.FragmentSearchBinding
import com.njuacha.searchflickr.ui.viewModel.MainActivityViewModel
import com.njuacha.searchflickr.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var adapter: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        // setup the toolbar
        binding.toolBar.inflateMenu(R.menu.menu_search_frag)
        // setup on search button click
        setUpSearchBtnClick()
        // init the adapter
        adapter = SearchAdapter()
        // connect adapter to recyclerview
        binding.searchRv.adapter = adapter
        // show a welcome text
        binding.infoTv.isVisible = true

        // if photo were previously loaded then show them
        viewModel.searchPhotosLiveData.value?.let {
            if (it.status == Status.SUCCESS) {
                it.data?.let { it1 -> adapter.setPhotos(it1) }
                binding.infoTv.isVisible = false
            }
        }

        // set up search history menu item click
        setUpSearchHistoryClick()
        return binding.root
    }

    private fun setUpSearchBtnClick() {
        binding.searchButton.setOnClickListener {
            // get the search text
            val searchText = binding.searchEditText.text.toString()
            if (searchText.isBlank()) {
                Toast.makeText(context, getString(R.string.search_input_hint), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // make call to search photo based on text
            viewModel.getPhotosFromSearch(searchText).observe(viewLifecycleOwner) { photosResource ->
                when(photosResource.status) {
                    Status.SUCCESS -> {
                        photosResource.data?.let { it1 -> adapter.setPhotos(it1) }
                        // when the photos are loaded, hide the progress bar
                        binding.progressBar.isVisible = false
                    }
                    Status.LOADING -> {
                        // show progress bar and hide welcome text
                        binding.infoTv.isVisible = false
                        binding.progressBar.isVisible = true
                    }
                    Status.ERROR -> {
                        // hide progress bar
                        binding.progressBar.isVisible = false
                        Toast.makeText(context,  photosResource.message, Toast.LENGTH_LONG).show()
                        // And then show back welcome text
                        binding.infoTv.isVisible = true
                    }
                }
            }

            // add searchText to search history
            viewModel.searchHistory.add(searchText)
        }
    }

    private fun setUpSearchHistoryClick() {
        binding.toolBar.menu.findItem(R.id.search_history).setOnMenuItemClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSearchHistoryDialog())
            false
        }
    }
}
