package org.alee.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import print

class RequestFlow {

    private val mRequester by lazy { DataRequester() }

    private val mFlow: MutableSharedFlow<String> by lazy { MutableSharedFlow(extraBufferCapacity = 1) }

    private val mOutFlow: Flow<String>

    private var mTimeOutJob: Job? = null

    private var mId = 1


    init {
        mOutFlow = createFlow()
    }


    private fun createFlow(): Flow<String> {

        return mFlow.onStart {
            "Flow Start".print()
        }.flowOn(Dispatchers.Default)
            .onEach { "Flow onEach ! Value = [$it]".print() }
            .map {
                if (it == "111") {
                    null
                } else
                    it
            }.flowOn(Dispatchers.Unconfined)
            .filterNotNull()
            .map {
                "doSomeThin".print()
                delay(1000L)
                it
            }
            .onCompletion {
                "Flow Completion".print()
            }.onEach {
                "Flow on Each Value = [$it]".print()
            }.onEach {
                "Stop Auto Time Out".print()
                cancelTimeAuto()
            }
    }


    fun request(): Flow<String> {
        GlobalScope.launch {
            launch {
                autoTimeOut()
            }
            launch {
            }
        }
        return mOutFlow
    }

    private fun autoTimeOut() {
        mTimeOutJob = GlobalScope.launch {
            delay(7 * 1000L)
            mFlow.tryEmit("Time Out")
        }
    }

    private fun cancelTimeAuto() {
        mTimeOutJob?.cancel()
    }

    suspend fun _request(): Flow<String>? {
        return withTimeoutOrNull(7 * 1000L) {
            flow {
                emit(mRequester.request(mId++))
            }.onStart {
                "Flow Start".print()
            }.flowOn(Dispatchers.Default)
                .onEach { "Flow onEach ! Value = [$it]".print() }
                .map {
                    if (it == "111") {
                        null
                    } else
                        it
                }.flowOn(Dispatchers.Unconfined)
                .filterNotNull()
                .map {
                    "doSomeThin".print()
                    delay(1000L)
                    it
                }
                .onCompletion {
                    "Flow Completion".print()
                }.onEach {
                    "Flow on Each Value = [$it]".print()
                }
        }
    }

}