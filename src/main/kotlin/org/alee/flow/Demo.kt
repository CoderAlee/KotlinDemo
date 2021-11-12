package org.alee.flow

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import print

suspend fun main() {
    "Start!!!".print()
    val job = GlobalScope.launch {
        RequestFlow().request().collect {
            it.print()
        }
    }
//    delay(6300L)
//    job.cancel()
    delay(8000L)
    "Over!!!".print()
}