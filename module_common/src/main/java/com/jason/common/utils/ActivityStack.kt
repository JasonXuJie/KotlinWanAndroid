package com.jason.common.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*
import kotlin.system.exitProcess

object ActivityStack {

    var activityStack : Stack<Activity> = Stack()


    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    fun currentActivity():Activity{
        return activityStack.lastElement()
    }

    fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    fun finishActivity(){
        finishActivity(activityStack.lastElement())
    }

    fun finishActivity(activity: Activity){
        activityStack.remove(activity)
        activity.finish()
    }


    fun finishActivity(cls: Class<*>) {
        for (i in activityStack.indices) {
            if (activityStack[i].javaClass == cls) {
                finishActivity(activityStack[i])
                removeActivity(activityStack[i])
                return
            }
        }
    }

    fun finishAllActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }

    fun AppExit(context: Context) {
        try {
            finishAllActivity()
            val activityMgr = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.restartPackage(context.getPackageName())
            exitProcess(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



}