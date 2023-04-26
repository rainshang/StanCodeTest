package com.example.android.pictureinpicture.timer

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface TimerRepo {

    /**
     * @return time on server
     */
    suspend fun getTime(): Long

    suspend fun getTimerState(): TimerState

    suspend fun setTimerState(state: TimerState)
}

class TimerRepoImpl @Inject constructor(private val timerLocalServer: TimerLocalServer) : TimerRepo {

    override suspend fun getTime(): Long {
        return System.currentTimeMillis()
    }

    override suspend fun getTimerState(): TimerState {
        return TimerState(
            timerLocalServer.isStarted,
            timerLocalServer.startedTime
        )
    }

    override suspend fun setTimerState(state: TimerState) {
        timerLocalServer.isStarted = state.isStarted
        timerLocalServer.startedTime = state.startedTimeOnServer
    }

}

class TimerLocalServer @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        private const val PREFS_NAME = "timer state"
        private const val KEY_IS_STARTED = "isStarted"
        private const val KEY_STARTED_TIME = "started time"
    }

    private val sp = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var isStarted: Boolean = sp.getBoolean(KEY_IS_STARTED, false)
        get() = sp.getBoolean(KEY_IS_STARTED, false)
        set(value) {
            field = value
            sp.edit { putBoolean(KEY_IS_STARTED, value) }
        }

    var startedTime: Long = if (isStarted) sp.getLong(KEY_STARTED_TIME, 0) else 0
        get() = if (isStarted) sp.getLong(KEY_STARTED_TIME, 0) else 0
        set(value) {
            field = value
            sp.edit { putLong(KEY_STARTED_TIME, value) }
        }
}