package com.example.headsandhandstest.kernel.application

class ValidationException : Exception() {
    val errors: MutableList<Any> = mutableListOf()

    fun addError(error: Any) {
        errors.add(error)
    }

    fun throwIfErrorsNotEmpty() {
        if (errors.isNotEmpty()) {
            throw this
        }
    }
}
