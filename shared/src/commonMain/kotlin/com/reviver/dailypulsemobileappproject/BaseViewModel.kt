package com.reviver.dailypulsemobileappproject

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val scope:CoroutineScope
}