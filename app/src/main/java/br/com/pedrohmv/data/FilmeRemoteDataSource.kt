package br.com.pedrohmv.data

import br.com.pedrohmv.domain.FilmeVideosResponse
import br.com.pedrohmv.domain.ListaFilmePaginacao
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmeRemoteDataSource {

    @GET("movie/popular")
    suspend fun obterFilmesPopulares(
        @Query("page")
        pagina: Int = 1
    ): ListaFilmePaginacao

    @GET("movie/{movieId}/similar")
    suspend fun obterFilmesSimilares(
        @Path("movieId")
        idFilme: Int
    ): ListaFilmePaginacao

    @GET("movie/{movieId}/videos")
    suspend fun obterVideosFilme(
        @Path("movieId")
        idFilme: Int
    ): FilmeVideosResponse

}