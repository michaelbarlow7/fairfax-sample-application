package com.michaelbarlow.fairfaxapplication

import android.os.Bundle
import android.support.test.runner.AndroidJUnitRunner
import io.appflate.restmock.RESTMockServerStarter
import io.appflate.restmock.android.AndroidAssetsFileParser

class FairfaxTestRunner : AndroidJUnitRunner(){

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        RESTMockServerStarter.startSync(AndroidAssetsFileParser(context))
    }
}