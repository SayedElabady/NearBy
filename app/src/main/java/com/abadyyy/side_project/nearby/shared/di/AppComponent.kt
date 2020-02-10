package com.abadyyy.side_project.nearby.shared.di

import android.app.Application
import com.abadyyy.side_project.nearby.shared.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        NetworkingModule::class,
        MapperModule::class,
        ObjectBoxModule::class,
        SchedulerProviderModule::class]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}