package com.njuacha.searchflickr.activity

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.njuacha.searchflickr.R
import com.njuacha.searchflickr.rest.ApiClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = ai.metaData["flickr_api_key"]

        val apiKey = value.toString()
        // set the api_key
        ApiClient.setApiKey(apiKey)
    }
}
