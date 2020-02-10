package com.abadyyy.side_project.nearby.shared.di


import dagger.Module

@Module
class MapperModule {

//    @Provides
//    @Singleton
//    @Named(CATEGORIES_MAPPER)
//    fun provideCategoriesMapper(): Mapper<CategoriesResponse.Category, CategoryEntity> {
//        return CategoryUIMapper()
//    }

    companion object {
        const val CATEGORIES_MAPPER = "categories_mapper"
    }
}