package com.abadyyy.side_project.nearby.shared.di
import androidx.lifecycle.ViewModelProvider
import com.abadyyy.side_project.nearby.shared.di.DaggerAwareViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilder {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory):
            ViewModelProvider.Factory
}