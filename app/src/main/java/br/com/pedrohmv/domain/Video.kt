package br.com.pedrohmv.domain

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val nome: String,
    @SerializedName("type")
    val tipo: String
)