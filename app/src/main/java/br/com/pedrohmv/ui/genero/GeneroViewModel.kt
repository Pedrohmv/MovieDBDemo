package br.com.pedrohmv.ui.genero

import androidx.lifecycle.MutableLiveData
import br.com.pedrohmv.data.FilmeRepository
import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.util.base.BaseViewModel
import br.com.pedrohmv.util.base.ErrorEvent
import br.com.pedrohmv.util.base.Event
import java.net.SocketTimeoutException

class GeneroViewModel(
    private val filmeRepository: FilmeRepository
) : BaseViewModel() {

    val filmeList = MutableLiveData<List<Filme>>()
    val events = MutableLiveData<Event>()

    fun obterFilmesPopulares() {
        launch({
            filmeList.value = filmeRepository.obterFilmesPopulares()
        },{
            events.value = if (it is SocketTimeoutException)
                ErrorEvent("Verifique sua conexão com a internet")
            else
                ErrorEvent("Não foi possível obter os filmes")
        })
    }

}