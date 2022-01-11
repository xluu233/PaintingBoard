package com.bugmaker.paintingboard

import android.app.Application

/**
 * @ClassName App
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2022/1/10 15:35
 */


class App : Application() {

    companion object{
        lateinit var application:Application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

}