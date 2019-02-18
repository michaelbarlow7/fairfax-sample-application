package com.michaelbarlow.fairfaxapplication

import io.appflate.restmock.RESTMockServer

/**
 * This file overrides the Constants file in the main project, allowing us to change the BASE_URL
 * used for network requests and direct requests to our mock web server.
 */
object Constants {

    val BASE_URL = RESTMockServer.getUrl()
}