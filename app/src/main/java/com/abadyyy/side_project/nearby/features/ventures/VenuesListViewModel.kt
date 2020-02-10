package com.abadyyy.side_project.nearby.features.ventures

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.abadyyy.side_project.nearby.shared.store.model.VenueRequest
import com.abadyyy.side_project.nearby.shared.store.model.VenuesUIModel
import com.abadyyy.side_project.nearby.shared.store.remote.datasource.LocationAPI
import com.abadyyy.side_project.nearby.shared.store.usecase.SingleUseCase
import com.abadyyy.side_project.nearby.shared.ui.BaseViewModel
import javax.inject.Inject

class VenuesListViewModel @Inject constructor(
    private val useCase: SingleUseCase<VenueRequest, List<VenuesUIModel>>
) : BaseViewModel() {

    val isVenuesEmpty = MutableLiveData<Boolean>()
    val ventures = MutableLiveData<List<VenuesUIModel>>()

    fun refreshData(latitude: Double, longitude: Double) {
        useCase.invoke(VenueRequest.create(latitude, longitude))
            .doOnSubscribe { isLoading.postValue(true) }
            .doFinally { isLoading.postValue(false) }
            .subscribe({
                ventures.postValue(it)
                if (it.isEmpty())
                    isVenuesEmpty.postValue(true)
                else isVenuesEmpty.postValue(false)
            }) {
                handleError(it) {
                    refreshData(latitude, longitude)
                }
            }.addToDisposableBag()
    }
}