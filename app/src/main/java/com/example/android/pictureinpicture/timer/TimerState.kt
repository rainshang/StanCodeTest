package com.example.android.pictureinpicture.timer

data class TimerState(
    val isStarted: Boolean,
    val startedTimeOnServer: Long // the moment when started (on server)
)
