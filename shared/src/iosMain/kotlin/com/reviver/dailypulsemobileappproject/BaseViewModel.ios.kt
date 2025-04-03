package com.reviver.dailypulsemobileappproject

import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel {
    actual val scope: CoroutineScope
        get() = TODO("Not yet implemented")
}