package com.abadyyy.side_project.nearby.shared.store.remote.repos

import com.abadyyy.side_project.nearby.shared.store.remote.datasource.VenuesAPI
import javax.inject.Inject

class VenuesRepository @Inject constructor(private val venuesAPI: VenuesAPI) : IVenusRepository {

    override fun getRemoteVenues(
        coordinates: String,
        latitudeLongitudeAccuracy: Double
    ) =
        venuesAPI.getNearestVenues(coordinates, latitudeLongitudeAccuracy)
}