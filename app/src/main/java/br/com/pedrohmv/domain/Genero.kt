package br.com.pedrohmv.domain

import com.google.gson.annotations.SerializedName

data class Genero(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)