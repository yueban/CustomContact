package com.yueban.customcontact

import android.app.Application
import android.content.Context

/**
 * @author yueban fbzhh007@gmail.com
 * @date 2019-11-30
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        private lateinit var context: MyApp

        fun getContext(): Context {
            return context
        }
    }
}