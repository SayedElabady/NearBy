package com.abadyyy.side_project.nearby.shared.store.remote

import com.abadyyy.side_project.nearby.shared.Constants
import com.abadyyy.side_project.nearby.shared.store.model.VenueBaseResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface INearByService {

    @GET(Constants.VENUES_ENDPOINT)
    fun getNearestVenues(
        @Query("ll") coordinates: String,
        @Query("llAcc") latitudeLongitudeAccuracy: Double
    ): Single<VenueBaseResponse>

}