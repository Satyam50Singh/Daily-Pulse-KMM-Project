package com.example.dailypulse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

actual open class BaseViewModel {
    actual val scope: CoroutineScope
        get() = CoroutineScope(Dispatchers.IO)

    fun clearScope() {
        scope.cancel()
    }
}