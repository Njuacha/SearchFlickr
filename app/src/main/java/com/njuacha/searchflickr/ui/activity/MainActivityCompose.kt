package com.njuacha.searchflickr.ui.activity

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.njuacha.searchflickr.ui.viewModel.MainActivityComposeViewModel
import com.njuacha.searchflickr.utils.ApiUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityCompose : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = ai.metaData["flickr_api_key"]
        val apiKey = value.toString()
        // set the api_key
        ApiUtil.setApiKey(apiKey)

        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp(myViewModel: MainActivityComposeViewModel = viewModel()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Super Search")},
                )
            },
            content = {
                val searchInputValue = remember { mutableStateOf(TextFieldValue())}

                Column(modifier = Modifier.padding(16.dp)) {
                    Row {
                        TextField(
                            value = searchInputValue.value,
                            onValueChange = {searchInputValue.value = it},
                            placeholder = { Text(text = "enter search text here")}
                        )
                        Button(onClick = {
                            myViewModel.loadPhotosBasedOnInput(searchInputValue.value.text)
                        }) {
                            Text(text = "Search")
                        }
                    }

                    // Display search results below
                    SearchResults(myViewModel)
                }
            }
        )
    }

    @Preview
    @Composable
    fun DefaultPreview() {
       MyApp()
    }

    @Composable
    fun SearchResults(myViewModel: MainActivityComposeViewModel) {
        val photos by myViewModel.photos.observeAsState(initial = emptyList())

        LazyColumn {
            items(photos) { photo ->
                val url =
                    "https://farm${photo.farm}.static.flickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
                PhotoCard(url = url)
            }
        }
    }

    @Composable
    fun PhotoCard(url: String) {
        AsyncImage(
            model = url,
            contentDescription = null
        )
    }


}