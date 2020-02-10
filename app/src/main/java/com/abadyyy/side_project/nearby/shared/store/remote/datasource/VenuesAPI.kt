package com.abadyyy.side_project.nearby.shared.store.remote.datasource

import com.abadyyy.side_project.nearby.shared.store.model.VenueBaseResponse
import com.abadyyy.side_project.nearby.shared.store.remote.INearByService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class VenuesAPI @Inject constructor(private val service: INearByService) {

    fun getNearestVenues(
        coordinates: String,
        latitudeLongitudeAccuracy: Double
    ): Single<VenueBaseResponse> =
        service.getNearestVenues(coordinates, latitudeLongitudeAccuracy)

}