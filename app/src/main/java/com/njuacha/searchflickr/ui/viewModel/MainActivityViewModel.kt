package com.njuacha.searchflickr.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njuacha.searchflickr.data.repository.MainRepository
import com.njuacha.searchflickr.data.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    val searchPhotosLiveData = MutableLiveData<List<Photo>>()
    val searchHistory = mutableListOf<String>()

    fun getPhotosFromSearch(searchText: String): LiveData<List<Photo>> {
        // create a new coroutine on the ui thread
        viewModelScope.launch {
            // make the call to get suspend function that gets pictures from flickr api
            mainRepository.getSearchPicturesFromApi(searchText).let { photosList ->
                // the photosList is returned back to the ui thread so we call setValue on liveData
                searchPhotosLiveData.value = photosList
            }
        }
        return searchPhotosLiveData
    }
}