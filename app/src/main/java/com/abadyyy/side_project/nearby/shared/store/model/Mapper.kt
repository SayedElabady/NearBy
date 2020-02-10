package com.abadyyy.side_project.nearby.shared.store.model

interface Mapper<I, O> {
    fun map(input: I): O

    fun revert(output: O): I
}