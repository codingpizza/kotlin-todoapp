package com.codingpizza.todoapp

import android.app.Application
import com.codingpizza.todoapp.di.todoModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class TodoAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger()
            androidContext(this@TodoAppApplication)
            modules(
                listOf(
                    todoModule
                )
            )
        }
    }
}