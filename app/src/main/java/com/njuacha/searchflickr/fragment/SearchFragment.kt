package com.njuacha.searchflickr.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        return binding.root
    }

    private fun setUpSearchBtnClick() {
        binding.searchButton.setOnClickListener {
            // get the search text
            val searchText = binding.searchEditText.text.toString()
            if (searchText.isBlank()) {
                Toast.makeText(context, "Please enter a search text", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // make call to search photo based on text
            viewModel.getPhotosFromSearch(searchText).observe(viewLifecycleOwner) { photosList ->
                if (!photosList.isNullOrEmpty()) {
                    adapter.setPhotos(photosList)
                }
            }
        }
    }

}
