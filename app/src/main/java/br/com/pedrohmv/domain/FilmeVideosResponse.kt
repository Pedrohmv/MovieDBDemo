package br.com.pedrohmv.domain

import com.google.gson.annotations.SerializedName

data class FilmeVideosResponse(
    @SerializedName("id")
    val filmeId: Int,
    @SerializedName("results")
    val videos: List<Video>
)