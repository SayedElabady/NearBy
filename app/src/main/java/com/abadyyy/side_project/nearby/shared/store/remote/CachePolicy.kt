package com.abadyyy.side_project.nearby.shared.store.remote

import javax.inject.Inject

class CachePolicy @Inject constructor(){
    fun provideCacheSize() = 10 * 1024 * 1024
}