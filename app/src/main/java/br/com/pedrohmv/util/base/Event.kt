package br.com.pedrohmv.util.base

sealed class Event

object LoadingEvent : Event()
class ErrorEvent(val errorMessage: String) : Event()
object SuccessEvent: Event()