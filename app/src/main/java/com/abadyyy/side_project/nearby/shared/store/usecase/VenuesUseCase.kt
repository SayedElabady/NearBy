package com.abadyyy.side_project.nearby.shared.store.usecase

import com.abadyyy.side_project.nearby.shared.Constants
import com.abadyyy.side_project.nearby.shared.store.model.Mapper
import com.abadyyy.side_project.nearby.shared.store.model.VenueBaseResponse
import com.abadyyy.side_project.nearby.shared.store.model.VenueRequest
import com.abadyyy.side_project.nearby.shared.store.model.VenuesUIModel
import com.abadyyy.side_project.nearby.shared.store.remote.repos.IVenusRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class VenuesUseCase @Inject constructor(
    private val repository: IVenusRepository,
    @Named(Constants.REGULAR_SCHEDULER_PROVIDER) provider: ISchedulerProvider,
    private val mapper: Mapper<VenueBaseResponse.VenueItemResponse, VenuesUIModel>
) : SingleUseCase<VenueRequest, List<VenuesUIModel>>(provider) {
    override fun buildObservable(input: VenueRequest): Single<List<VenuesUIModel>> {
        return repository.getRemoteVenues(input.coordinatesString, input.accuracy).map { response ->
            val emptyVenues = mutableListOf<VenueBaseResponse.VenueItemResponse>()
            response.response.groups.forEach { venueGroup ->
                venueGroup.venueItems.forEach { venueWrapper ->
                    emptyVenues.add(venueWrapper.venue)
                }
            }
            emptyVenues.map { mapper.map(it) }
        }

    }
}