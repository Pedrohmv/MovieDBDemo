package br.com.pedrohmv

import android.util.Log
import br.com.pedrohmv.util.base.AppCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TesteCoroutineScope : AppCoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Unconfined


    override fun launchOnUI(block: suspend CoroutineScope.() -> Unit, errorFunction: ErrorFunction?) {
        launch(coroutineContext) {
            try {
                block()
            } catch (error: Throwable) {
                if (errorFunction == null) Log.e("TESTE-MOVIEDEMO", error.message, error)
                else errorFunction(error)
            }
        }
    }

}

typealias ErrorFunction = (Throwable) -> Unit