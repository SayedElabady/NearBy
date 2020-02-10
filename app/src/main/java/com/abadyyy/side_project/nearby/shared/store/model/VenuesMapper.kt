package com.abadyyy.side_project.nearby.shared.store.model

import javax.inject.Inject

class VenuesMapper @Inject constructor() :
    Mapper<VenueBaseResponse.VenueItemResponse, VenuesUIModel> {
    override fun map(input: VenueBaseResponse.VenueItemResponse): VenuesUIModel {
        return VenuesUIModel(
            input.id, input.name, input.addressWrapper.addressString.joinToString(separator = ", ")
        )
    }

    override fun revert(output: VenuesUIModel): VenueBaseResponse.VenueItemResponse {
        return VenueBaseResponse.VenueItemResponse(
            output.id, output.name, VenueBaseResponse.VenueItemResponse.VenuePhotoGroup(
                listOf()
            ), VenueBaseResponse.VenueItemResponse.AddressWrapper(output.address.split(", "))
        )
    }

}