package br.com.pedrohmv.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Filme(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("poster_path")
    val caminhoPoster: String,
    @SerializedName("genre_ids")
    val listaIdGenero: List<Int>,
    @SerializedName("overview")
    val sinopse: String
) : Parcelable