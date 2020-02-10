package com.abadyyy.side_project.nearby.shared.store.model

data class VenueRequest(val coordinatesString: String, val accuracy: Double) {

    companion object {
        fun create(lat: Double, long: Double, accuracy: Double = 500.0) = VenueRequest(
            "$lat,$long", accuracy
        )
    }

}