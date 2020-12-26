package com.codingpizza.todoapp

import android.app.Application
import com.codingpizza.todoapp.di.todoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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