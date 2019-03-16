package br.com.pedrohmv

import android.app.Application
import br.com.pedrohmv.di.appModules
import br.com.pedrohmv.di.remoteModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(remoteModules, appModules))
    }

}