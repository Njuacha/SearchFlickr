package com.njuacha.searchflickr.ui.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njuacha.searchflickr.data.model.Photo
import com.njuacha.searchflickr.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityComposeViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    fun loadPhotosBasedOnInput(searchString: String) {
        viewModelScope.launch {
            mainRepository.getSearchPicturesFromApi(searchString)?.let { photoResponse ->
                // set photos to list of photos
                _photos.value = photoResponse.body().photos.photos
            }
        }
    }


}