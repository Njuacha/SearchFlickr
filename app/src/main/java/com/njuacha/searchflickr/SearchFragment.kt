package com.njuacha.searchflickr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.njuacha.searchflickr.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        // setup the toolbar
        binding.toolBar.inflateMenu(R.menu.menu_search_frag)

        return binding.root
    }

}
