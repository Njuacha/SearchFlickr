package com.njuacha.searchflickr.ui.activity

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.njuacha.searchflickr.R
import com.njuacha.searchflickr.data.rest.ApiUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = ai.metaData["flickr_api_key"]

        val apiKey = value.toString()
        // set the api_key
        ApiUtil.setApiKey(apiKey)
    }
}
