package com.abadyyy.side_project.nearby.features.ventures

import androidx.lifecycle.ViewModel
import com.abadyyy.side_project.nearby.shared.di.ViewModelKey
import com.abadyyy.side_project.nearby.shared.store.model.*
import com.abadyyy.side_project.nearby.shared.store.remote.repos.IVenusRepository
import com.abadyyy.side_project.nearby.shared.store.remote.repos.VenuesRepository
import com.abadyyy.side_project.nearby.shared.store.usecase.SingleUseCase
import com.abadyyy.side_project.nearby.shared.store.usecase.VenuesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [VenuesListModule.Extension::class])
abstract class VenuesListModule {

    @Binds
    @IntoMap
    @ViewModelKey(VenuesListViewModel::class)
    abstract fun providesViewModel(viewModel: VenuesListViewModel): ViewModel

    @Binds
    abstract fun provideRepo(repo: VenuesRepository): IVenusRepository

    @Module
    class Extension {

        @Provides
        fun provideUseCase(venuesUseCase: VenuesUseCase): SingleUseCase<VenueRequest, List<VenuesUIModel>> {
            return venuesUseCase
        }

        @Provides
        fun provideMapper(mapper: VenuesMapper): Mapper<VenueBaseResponse.VenueItemResponse, VenuesUIModel> {
            return mapper
        }


    }
}