package com.michaelbarlow.fairfaxapplication

data class Resource<T>(val status:Status, val data:T? = null) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}