package com.permission.mylibrary

import androidx.fragment.app.FragmentActivity

/**
 * @Description
 * @Author created by 张永宏
 * date :2021/6/30 9:57 AM
 */
object PermissionX {
    private val TAG="InvisibleFragment"
    fun request(activity:FragmentActivity,vararg permissions: String,callback: PermissionCallback){
        val fragmentManager=activity.supportFragmentManager
        val existedFragment=fragmentManager.findFragmentByTag(TAG)
        val fragment=if (existedFragment!=null){
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment=InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback,*permissions)
    }
}