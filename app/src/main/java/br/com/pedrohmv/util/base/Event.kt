package br.com.pedrohmv.util.base

sealed class Event

class LoadingEvent : Event()
class ErrorEvent(errorMessage: String) : Event()