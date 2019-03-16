package br.com.pedrohmv.ui.detalhe

import androidx.lifecycle.MutableLiveData
import br.com.pedrohmv.data.FilmeRepository
import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.domain.Video
import br.com.pedrohmv.util.base.BaseViewModel

class DetalheFilmeViewModel(
    private val filmeRepository: FilmeRepository
) : BaseViewModel() {

    val filmesSimilares = MutableLiveData<List<Filme>>()
    val videos = MutableLiveData<List<Video>>()

    fun obterFilmesSimilares(idFilme: Int){
        launch({
            filmesSimilares.value = filmeRepository.obterFilmesSimilares(idFilme)
        },{

        })
    }

    fun obterVideosFilme(idFilme: Int){
        launch({
            videos.value = filmeRepository.obterVideosFilme(idFilme)
        },{

        })
    }

}