package com.reviver.dailypulsemobileappproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel:ViewModel() {
    actual val scope: CoroutineScope = viewModelScope
}