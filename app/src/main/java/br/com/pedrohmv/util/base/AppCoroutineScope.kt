package br.com.pedrohmv.util.base

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AppCoroutineScope : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main


    fun launchOnUI(block: suspend CoroutineScope.() -> Unit, errorFunction: ErrorFunction? = null) {
        launch(coroutineContext) {
            try {
                block()
            } catch (error: Throwable) {
                if (errorFunction == null) Log.e("MOVIEDEMO", error.message, error)
                else errorFunction(error)
            }
        }
    }

}

typealias ErrorFunction = (Throwable) -> Unit
