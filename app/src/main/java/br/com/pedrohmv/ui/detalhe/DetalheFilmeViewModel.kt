package br.com.pedrohmv.ui.detalhe

import androidx.lifecycle.MutableLiveData
import br.com.pedrohmv.data.FilmeRepository
import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.domain.Video
import br.com.pedrohmv.util.base.BaseViewModel
import br.com.pedrohmv.util.base.SingleLiveEvent

class DetalheFilmeViewModel(
    private val filmeRepository: FilmeRepository
) : BaseViewModel() {

    val filmesSimilares = SingleLiveEvent<List<Filme>>()
    val videos = SingleLiveEvent<List<Video>>()

    fun obterFilmesSimilares(idFilme: Int){
        launch({
            filmesSimilares.value = filmeRepository.obterFilmesSimilares(idFilme)
        },{
//            tratar erros
        })
    }

    fun obterVideosFilme(idFilme: Int){
        launch({
            videos.value = filmeRepository.obterVideosFilme(idFilme)
        },{
//            tratar erros
        })
    }

}