package com.abadyyy.side_project.nearby.shared.store.model

import com.google.gson.annotations.SerializedName

data class VenueBaseResponse(@SerializedName("response") val response: VenueResponse) {
    data class VenueResponse(@SerializedName("groups") val groups: List<VenueGroup>)
    data class VenueGroup(@SerializedName("items") val venueItems: List<VenueItemWrapper>)
    data class VenueItemWrapper(@SerializedName("venue") val venue: VenueItemResponse)


    data class VenueItemResponse(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("photos") val photos: VenuePhotoGroup,
        @SerializedName("location") val addressWrapper: AddressWrapper
    ) {
        data class AddressWrapper(@SerializedName("formattedAddress") val addressString: List<String>)
        data class VenuePhotoGroup(@SerializedName("groups") val groups: List<VenuePhoto>)
        data class VenuePhoto(
            val suffix: String,
            val prefix: String,
            val width: Int,
            val height: Int
        )
    }
}