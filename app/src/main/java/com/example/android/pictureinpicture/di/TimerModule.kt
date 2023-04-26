package com.example.android.pictureinpicture.di

import com.example.android.pictureinpicture.timer.TimerRepo
import com.example.android.pictureinpicture.timer.TimerRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TimerModule {
    @Binds
    fun bindTimerRepo(timerRepoImpl: TimerRepoImpl): TimerRepo
}