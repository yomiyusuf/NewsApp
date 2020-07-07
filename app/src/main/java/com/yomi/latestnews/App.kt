package com.yomi.latestnews

import android.app.Application
import com.yomi.latestnews.di.appComponent
import com.yomi.latestnews.util.InternetUtil
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Yomi Joseph on 2020-07-07.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        InternetUtil.init(this)
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(provideDependency())
        }
    }

    fun provideDependency() = appComponent
}