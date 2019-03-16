package br.com.pedrohmv.util.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    val coroutineScope = AppCoroutineScope()

    fun launch(block: suspend CoroutineScope.() -> Unit, errorFunction: ErrorFunction? = null){
        coroutineScope.launchOnUI(block, errorFunction)
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.coroutineContext.cancel()
    }

}