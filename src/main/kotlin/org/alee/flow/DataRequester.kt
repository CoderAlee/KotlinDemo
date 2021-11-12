package org.alee.flow

import kotlinx.coroutines.*
import print
import safeCall
import kotlin.coroutines.resume

class DataRequester {

    suspend fun request(id: Int): String = suspendCancellableCoroutine {
        "DataRequester start request! id = $id".print()
        safeCall {
            "DataRequester Start Sleep".print()
            Thread.sleep(5 * 1000L)
            "DataRequester Sleep End".print()
        }
        it.resume("Request Success")
    }
}