package com.jason.common.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.permissionx.guolindev.PermissionX


object PermissionUtil {

    fun request(activity:AppCompatActivity,permissions:MutableList<String>,grantCallback:()->Unit,deniedCallback:()->Unit){
        PermissionX.init(activity).permissions(permissions).request {
                allGranted, grantedList, deniedList ->
            if (allGranted){
                grantCallback()
            }else{
                deniedCallback()
            }
        }
    }

    fun request(fragment:Fragment, permissions: MutableList<String>,grantCallback:()->Unit,deniedCallback:()->Unit){
        PermissionX.init(fragment).permissions(permissions).request {
                allGranted, grantedList, deniedList ->
            if (allGranted){
                grantCallback()
            }else{
                deniedCallback()
            }
        }
    }


}