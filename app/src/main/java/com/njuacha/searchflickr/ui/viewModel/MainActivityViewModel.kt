package com.njuacha.searchflickr.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njuacha.searchflickr.data.repository.MainRepository
import com.njuacha.searchflickr.data.model.Photo
import com.njuacha.searchflickr.utils.NetworkHelper
import com.njuacha.searchflickr.utils.Resource
import com.njuacha.searchflickr.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _searchPhotosLiveData = MutableLiveData<Resource<List<Photo>>>()
    val searchPhotosLiveData: LiveData<Resource<List<Photo>>>
        get() = _searchPhotosLiveData
    val searchHistory = mutableListOf<String>()

    fun getPhotosFromSearch(searchText: String): LiveData<Resource<List<Photo>>> {
        // create a new coroutine on the ui thread
        viewModelScope.launch {
            _searchPhotosLiveData.value = Resource.loading(null)
            // make the call to get suspend function that gets pictures from flickr api
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getSearchPicturesFromApi(searchText).let { photosList ->
                    // the photosList is returned back to the ui thread so we call setValue on liveData
                    _searchPhotosLiveData.value = Resource.success(photosList)
                }

            } else {
                _searchPhotosLiveData.value = Resource.error("No internet connection", null)
            }

        }
        return searchPhotosLiveData
    }
}