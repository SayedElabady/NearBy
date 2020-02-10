package com.abadyyy.side_project.nearby.shared.di


import com.abadyyy.side_project.nearby.shared.Constants.REGULAR_SCHEDULER_PROVIDER
import com.abadyyy.side_project.nearby.shared.Constants.UI_OBSERVER_SCHEDULER_PROVIDER
import com.abadyyy.side_project.nearby.shared.store.usecase.ISchedulerProvider
import com.abadyyy.side_project.nearby.shared.store.usecase.SchedulerProvider
import com.abadyyy.side_project.nearby.shared.store.usecase.UIObserverSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class SchedulerProviderModule {
    @Provides
    @Singleton
    @Named(REGULAR_SCHEDULER_PROVIDER)
    fun provideSchedulerProvider(): ISchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    @Singleton
    @Named(UI_OBSERVER_SCHEDULER_PROVIDER)
    fun provideUIObserverSchedulerProvider(): ISchedulerProvider {
        return UIObserverSchedulerProvider()
    }

}