package com.michaelbarlow.fairfaxapplication

import android.os.Bundle
import android.support.test.runner.AndroidJUnitRunner
import io.appflate.restmock.RESTMockServerStarter
import io.appflate.restmock.android.AndroidAssetsFileParser

class FairfaxTestRunner : AndroidJUnitRunner(){

    /**
     * This starts the RESTMockServer.
     * Ordinarily we could just use the TestRunner provided with the library but it seems
     * to activate https by default
     */
    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        RESTMockServerStarter.startSync(AndroidAssetsFileParser(context))
    }
}