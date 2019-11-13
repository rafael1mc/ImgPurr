package com.example.imgpurr

import android.app.Application
import com.example.imgpurr.di.repositoriesModule
import com.example.imgpurr.di.utilsModule
import com.example.imgpurr.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModules = listOf(
            viewModelsModule,
            repositoriesModule,
            utilsModule
        )
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }

    }

}