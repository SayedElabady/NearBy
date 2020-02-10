package com.abadyyy.side_project.nearby.shared.di


import com.abadyyy.side_project.nearby.shared.store.remote.interceptor.JsonInterceptor
import android.content.Context
import com.abadyyy.side_project.nearby.shared.Constants
import com.abadyyy.side_project.nearby.shared.store.remote.CachePolicy
import com.abadyyy.side_project.nearby.shared.store.remote.INearByService
import com.abadyyy.side_project.nearby.shared.store.remote.interceptor.AuthInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkingModule {

    @Provides
    @Singleton
    fun provideApiClient(
        context: Context,
        jsonInterceptor: JsonInterceptor,
        authInterceptor: AuthInterceptor,
        gson: Gson,
        cachePolicy: CachePolicy
    ): Retrofit {
        val cacheSize = cachePolicy.provideCacheSize()
        val dir = context.cacheDir
        val cache = Cache(dir, cacheSize.toLong())

        val client = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(jsonInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesJsonInterceptor(): JsonInterceptor {
        return JsonInterceptor()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideNearByService(retrofit: Retrofit): INearByService {
        return retrofit.create(INearByService::class.java)
    }


}