package com.abadyyy.side_project.nearby.shared.store.remote.interceptor

import com.abadyyy.side_project.nearby.shared.Constants
import okhttp3.Interceptor
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val dateFormatter = SimpleDateFormat("YYYYMMDD", Locale.getDefault())

        val url = request.url.newBuilder()
            .addQueryParameter(Constants.CLIENT_ID_QUERY, Constants.CLIENT_ID)
            .addQueryParameter(Constants.CLIENT_SECRET_QUERY, Constants.CLIENT_SECRET)
            .addQueryParameter(Constants.DATE_QUERY, dateFormatter.format(Date()))
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}