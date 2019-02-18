package com.michaelbarlow.fairfaxapplication

/**
 * This class contains the status of the load data operation,
 * and any data itself it is successful
 */
data class Resource<T>(val status:Status, val data:T? = null) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}