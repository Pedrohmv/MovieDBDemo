package br.com.pedrohmv.domain

import com.google.gson.annotations.SerializedName

data class ListaFilmePaginacao(
    @SerializedName("page")
    val paginaAtual: Int,
    @SerializedName("total_pages")
    val quantidadeDePaginas: Int,
    @SerializedName("results")
    val listaFilmes: List<Filme>
)