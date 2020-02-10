package com.abadyyy.side_project.nearby.shared.store.usecase

import com.abadyyy.side_project.nearby.shared.store.usecase.ISchedulerProvider
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulerProvider @Inject constructor() : ISchedulerProvider {
    override fun providePostExecuteScheduler() = Schedulers.io()

    override fun provideWorkerScheduler() = Schedulers.io()

}