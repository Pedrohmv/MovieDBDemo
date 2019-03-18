package br.com.pedrohmv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.*
import org.junit.Rule
import org.mockito.ArgumentCaptor

abstract class BaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    inline fun <reified T> argumentCaptor(): ArgumentCaptor<T> =
        ArgumentCaptor.forClass(T::class.java)

    fun <T> startCoroutine(code: suspend CoroutineScope.() -> T): T =
        runBlocking(Dispatchers.Unconfined, block = code)

}