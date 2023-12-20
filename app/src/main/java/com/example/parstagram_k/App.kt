package com.example.parstagram_k

import android.app.Application
import com.parse.BuildConfig
import com.parse.Parse
import com.parse.ParseObject

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ParseObject.registerSubclass(Post::class.java)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(APP_ID)
                .clientKey(CLIENT_KEY)
                .server(getString(R.string.back4app_server_url))
                .build());
    }

    companion object {
        private const val APP_ID = com.example.parstagram_k.BuildConfig.APP_ID
        private const val CLIENT_KEY = com.example.parstagram_k.BuildConfig.CLIENT_KEY
    }
}