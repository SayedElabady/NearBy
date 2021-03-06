package com.abadyyy.side_project.nearby.shared

import com.abadyyy.side_project.nearby.shared.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out App> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}