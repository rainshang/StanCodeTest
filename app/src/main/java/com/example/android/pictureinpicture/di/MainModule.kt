package com.example.android.pictureinpicture.di

import android.os.SystemClock
import com.example.android.pictureinpicture.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun provideUptimeProvider(): MainViewModel.UptimeProvider = object : MainViewModel.UptimeProvider {
        override fun uptimeMillis(): Long = SystemClock.uptimeMillis()
    }
}