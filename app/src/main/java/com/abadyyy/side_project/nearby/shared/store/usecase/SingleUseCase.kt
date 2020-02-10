package com.abadyyy.side_project.nearby.shared.store.usecase

import io.reactivex.Observable
import io.reactivex.Single

abstract class SingleUseCase<I, O>(private val schedulerProvider: ISchedulerProvider) {

    operator fun invoke(
        input: I
    ): Single<O> {
        return buildObservable(input)
            .subscribeOn(schedulerProvider.provideWorkerScheduler())
            .observeOn(schedulerProvider.providePostExecuteScheduler())
    }

    abstract fun buildObservable(input: I): Single<O>
}