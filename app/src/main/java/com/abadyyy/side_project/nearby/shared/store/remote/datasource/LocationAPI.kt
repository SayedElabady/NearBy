package com.abadyyy.side_project.nearby.shared.store.remote.datasource

import android.app.Activity
import android.location.Location
import com.google.android.gms.location.*
import java.lang.ref.WeakReference
import android.Manifest
import android.content.Intent
import android.os.Looper
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.tasks.Task
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class LocationAPI(
    activity: Activity,
    val currentMode: () -> Mode,
    val onLocationSuccess: (Location) -> Unit,
    val onFailure: (Error) -> Unit
) {
    val requiredPermissions = ArrayList<String>().apply {
        add(Manifest.permission.ACCESS_FINE_LOCATION)
        add(Manifest.permission.ACCESS_COARSE_LOCATION)
    }
    val activityReference = WeakReference<Activity>(activity)
    var fusedLocationProvider: FusedLocationProviderClient? = null
    var lastLocation: Location? = null
    val resolutionCheckRequest = 3232
    private var locationCallback: LocationCallback? = null

    fun refreshLocation() {
        checkPermissionsBeforeFetching()
    }

    private fun checkPermissionsBeforeFetching() {
        Dexter.withActivity(activityReference.get() as Activity)
            .withPermissions(requiredPermissions)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (it.grantedPermissionResponses.isNotEmpty()) {
                            startCheckingLocation()
                        } else onFailure(Error.PermissionNotGranted)
                    } ?: onFailure(Error.PermissionNotGranted)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                }
            }).check()
    }


    fun startCheckingLocation() {
        fusedLocationProvider =
            LocationServices.getFusedLocationProviderClient(activityReference.get() as Activity)
        val locationTask = fusedLocationProvider?.lastLocation

        locationTask?.addOnSuccessListener {
            it?.let {
                if (lastLocation != it) onLocationSuccess(it)
                lastLocation = it
                if (currentMode() == Mode.RealTime)
                    requestRealTimeListening()
            } ?: requestRealTimeListening()
        }

        locationTask?.addOnFailureListener {
            onFailure(Error.PermissionNotGranted)
            requestRealTimeListening()
        }
    }

    private fun getLocation() {
        if (activityReference.get() == null) {
            return
        }

        val locationRequest = LocationRequest().apply {
            interval = 10000
            fastestInterval = 2000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val locationTask: Task<LocationSettingsResponse> =
            (LocationServices.getSettingsClient(activityReference.get() as Activity))
                .checkLocationSettings(
                    (LocationSettingsRequest.Builder().addLocationRequest(
                        locationRequest
                    )).build()
                ).apply {
                    addOnSuccessListener { _ ->
                        fusedLocationProvider?.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            Looper.myLooper()
                        )
                    }

                    addOnFailureListener { exception ->
                        if (exception is ResolvableApiException) {
                            if (activityReference.get() == null) {
                                return@addOnFailureListener
                            }
                            exception.startResolutionForResult(
                                activityReference.get() as Activity,
                                resolutionCheckRequest
                            )

                        }
                    }
                }
    }

    private fun requestRealTimeListening() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                lastLocation?.let {
                    if (locationResult.lastLocation.distanceTo(lastLocation) >= 0.5)
                        onLocationSuccess(locationResult.lastLocation)
                } ?: onLocationSuccess(locationResult.lastLocation)
                lastLocation = locationResult.lastLocation

                if (currentMode() != Mode.RealTime)
                    fusedLocationProvider?.removeLocationUpdates(locationCallback)
            }

            override fun onLocationAvailability(locationAvailability: LocationAvailability?) {
                super.onLocationAvailability(locationAvailability)
                if (locationAvailability?.isLocationAvailable == false) {
                    onFailure(Error.LocationNotAvailable)
                    fusedLocationProvider?.removeLocationUpdates(locationCallback)
                }
            }
        }
        getLocation()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == resolutionCheckRequest && requestCode == Activity.RESULT_OK) {
            getLocation()
        } else {
            onFailure(Error.ResolutionNotGranted)
        }
    }

    enum class Mode {
        Single, RealTime
    }

    enum class Error {
        PermissionNotGranted,
        LocationNotAvailable,
        ResolutionNotGranted
    }
}