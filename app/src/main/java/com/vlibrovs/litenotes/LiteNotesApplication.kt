package com.vlibrovs.litenotes

import android.app.Application
import com.vlibrovs.litenotes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LiteNotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LiteNotesApplication)
            modules(appModule)
        }
    }

}