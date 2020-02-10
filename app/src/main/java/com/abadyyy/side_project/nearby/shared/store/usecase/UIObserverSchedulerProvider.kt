package com.abadyyy.side_project.nearby.shared.store.usecase

import com.abadyyy.side_project.nearby.shared.store.usecase.ISchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UIObserverSchedulerProvider @Inject constructor() : ISchedulerProvider {
    override fun providePostExecuteScheduler() = Schedulers.io()

    override fun provideWorkerScheduler(): Scheduler = AndroidSchedulers.mainThread()
}