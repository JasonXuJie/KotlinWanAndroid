package com.jason.common.utils

object CrashHandler : Thread.UncaughtExceptionHandler{

    private val crashHandler = Thread.getDefaultUncaughtExceptionHandler()

    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(p0: Thread, p1: Throwable) {

    }
}