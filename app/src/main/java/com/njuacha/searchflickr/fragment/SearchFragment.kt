package com.njuacha.searchflickr.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.njuacha.searchflickr.R
import com.njuacha.searchflickr.adapter.SearchAdapter
import com.njuacha.searchflickr.databinding.FragmentSearchBinding
import com.njuacha.searchflickr.viewModel.MainActivityViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by activityViewModels()
    lateinit var adapter: SearchAdapter


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
            adapter.setPhotos(it)
            binding.infoTv.isVisible = false
        }
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
            // show progress bar and hide welcome text
            binding.infoTv.isVisible = false
            binding.progressBar.isVisible = true

            // make call to search photo based on text
            viewModel.getPhotosFromSearch(searchText).observe(viewLifecycleOwner) { photosList ->
                // when the photos are loaded, hide the progress bar
                binding.progressBar.isVisible = false
                if (!photosList.isNullOrEmpty()) {
                    adapter.setPhotos(photosList)
                } else {
                    // inform the user that there are no images for inputed text
                    Toast.makeText(
                        context,
                        getString(R.string.msg_no_photos),
                        Toast.LENGTH_LONG
                    ).show()
                    // And then show back welcome text
                    binding.infoTv.isVisible = true
                }
            }
        }
    }

}
