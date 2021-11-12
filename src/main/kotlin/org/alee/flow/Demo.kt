package org.alee.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import print

val flow = RequestFlow()

suspend fun main() {
    "Start!!!".print()
    doRequest("AAAAAAAA")
    delay(5000L)
    doRequest("BBBBBBBB")
    delay(18000L)
    "Over!!!".print()
}

private fun doRequest(name: String) {
    GlobalScope.launch {
        "$name start request".print()
        val result = flow._request()
        if (null == result)
            "$name run Time Out".print()
        else
            result.collect { it.print() }
        "$name request End".print()
    }
}