package com.njuacha.searchflickr.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.njuacha.searchflickr.databinding.FragmentSearchHistoryBinding
import com.njuacha.searchflickr.viewModel.MainActivityViewModel

class SearchHistoryDialog : DialogFragment() {

    private var _binding: FragmentSearchHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        context?.let { mContext ->
            _binding = FragmentSearchHistoryBinding.inflate(LayoutInflater.from(mContext))
            val builder = AlertDialog.Builder(mContext)
            builder.setTitle("Your Search History")
                .setView(binding.root)
                .setPositiveButton("OK",null)
            // create an adapter with search history stored in view model
            val adapter = ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, viewModel.searchHistory)
            binding.searchHistoryLv.adapter = adapter
            return builder.create()
        }

        return super.onCreateDialog(savedInstanceState)
    }

}