package com.abadyyy.side_project.nearby.shared.store.remote.repos

import com.abadyyy.side_project.nearby.shared.store.model.VenueBaseResponse
import io.reactivex.Observable
import io.reactivex.Single

interface IVenusRepository {
    fun getRemoteVenues(
        coordinates: String,
        latitudeLongitudeAccuracy: Double = 500.0
    ): Single<VenueBaseResponse>

}