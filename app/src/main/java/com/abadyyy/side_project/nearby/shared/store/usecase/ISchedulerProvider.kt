package com.abadyyy.side_project.nearby.shared.store.usecase

import io.reactivex.Scheduler

interface ISchedulerProvider {

    fun provideWorkerScheduler(): Scheduler

    fun providePostExecuteScheduler(): Scheduler
}