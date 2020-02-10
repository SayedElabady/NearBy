package com.abadyyy.side_project.nearby.shared.di


import com.abadyyy.side_project.nearby.features.ventures.VenuesListActivity
import com.abadyyy.side_project.nearby.features.ventures.VenuesListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(
        modules = [VenuesListModule::class]
    )
    internal abstract fun bindsCategoriesActivity(): VenuesListActivity
}