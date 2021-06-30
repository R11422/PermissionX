package com.permission.mylibrary

import android.content.pm.PackageManager
import android.util.Log
import androidx.fragment.app.Fragment

/**
 * @Description
 * @Author created by 张永宏
 * date :2021/6/29 2:28 PM
 */
typealias PermissionCallback=(Boolean,List<String>)->Unit
class InvisibleFragment:Fragment() {
    private val TAG = "InvisibleFragment"
    private var callback:PermissionCallback?=null
    fun requestNow(cb:PermissionCallback,vararg permissions:String){
        callback=cb
        requestPermissions(permissions,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.i(TAG, "onRequestPermissionsResult: "+requestCode)
        if(requestCode==1){
            val deniedList=ArrayList<String>()

            for ((index,result) in grantResults.withIndex()){
                if(result!=PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            val allGranted=deniedList.isEmpty()
            Log.i(TAG, "onRequestPermissionsResult: "+allGranted)
            callback?.let { it(allGranted,deniedList) }
        }
    }
}