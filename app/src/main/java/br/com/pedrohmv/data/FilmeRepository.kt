package br.com.pedrohmv.data

import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.domain.Video

interface FilmeRepository {

    suspend open fun obterFilmesPopulares(idGenero: Int?, tituloFilme: String?): List<Filme>

    suspend open fun obterFilmesPopularesRemote()

    suspend fun obterFilmesSimilares(idFilme: Int): List<Filme>?

    suspend fun obterFilmesSimilaresRemote(idFilme: Int)

    suspend fun obterVideosFilme(idFilme: Int): List<Video>?

    suspend fun obterVideosFilmeRemote(idFilme: Int)

}

class FilmeRepositoryImpl(
    private val filmeRemoteDataSource: FilmeRemoteDataSource
) : FilmeRepository {

    private val cachedFilmeList = mutableSetOf<Filme>()
    private val cachedFilmesSimilares = mutableMapOf<Int, List<Filme>>()
    private val cachedVideosFilme = mutableMapOf<Int, List<Video>>()

    override suspend fun obterFilmesPopulares(idGenero: Int?, tituloFilme: String?): List<Filme> {
        if (cachedFilmeList.isEmpty()) obterFilmesPopularesRemote()
        return cachedFilmeList.toList().filter {
            (it.listaIdGenero.contains(idGenero) or (idGenero == null)) and
                    (it.titulo.contains(tituloFilme ?: "", true) or (tituloFilme.isNullOrEmpty()))
        }
    }

    override suspend fun obterFilmesPopularesRemote() {
        cachedFilmeList.addAll(filmeRemoteDataSource.obterFilmesPopulares().listaFilmes)
    }

    override suspend fun obterFilmesSimilares(idFilme: Int): List<Filme>? {
        if (cachedFilmesSimilares[idFilme].isNullOrEmpty()) obterFilmesSimilaresRemote(idFilme)
        return cachedFilmesSimilares[idFilme]
    }

    override suspend fun obterFilmesSimilaresRemote(idFilme: Int) {
        cachedFilmesSimilares[idFilme] = filmeRemoteDataSource.obterFilmesSimilares(idFilme).listaFilmes
    }

    override suspend fun obterVideosFilme(idFilme: Int): List<Video>? {
        if (cachedVideosFilme[idFilme].isNullOrEmpty()) obterVideosFilmeRemote(idFilme)
        return cachedVideosFilme[idFilme]
    }

    override suspend fun obterVideosFilmeRemote(idFilme: Int) {
        cachedVideosFilme[idFilme] = filmeRemoteDataSource.obterVideosFilme(idFilme).videos
    }

}