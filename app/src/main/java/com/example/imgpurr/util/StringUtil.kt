package com.example.imgpurr.util

import android.content.Context

class StringUtil(private val context: Context) {

    fun getFromResources(resourceId: Int): String = context.getString(resourceId)

}