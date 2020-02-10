package com.abadyyy.side_project.nearby.shared.store.model

import com.abadyyy.side_project.nearby.shared.store.model.ErrorModel

interface IErrorHandler{

    fun getError(throwable: Throwable) : ErrorModel
}