package br.com.pedrohmv.ui.genero

import androidx.lifecycle.MutableLiveData
import br.com.pedrohmv.data.FilmeRepository
import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.util.base.*
import java.net.SocketTimeoutException

class GeneroViewModel(
    private val filmeRepository: FilmeRepository
) : BaseViewModel() {

    val filmeList = SingleLiveEvent<List<Filme>>()
    val events = SingleLiveEvent<Event>()

    fun obterFilmesPopulares(idGenero: Int?, tituloFilme: String? = null) {
        launch({
            events.value = LoadingEvent
            filmeList.value = filmeRepository.obterFilmesPopulares(idGenero, tituloFilme)
            if (filmeList.value.isNullOrEmpty()) events.value = ErrorEvent("Nenhum filme encontrado.")
            events.value = SuccessEvent
        },{
            events.value = if (it is SocketTimeoutException)
                ErrorEvent("Verifique sua conexão com a internet")
            else
                ErrorEvent("Não foi possível obter os filmes")
        })
    }

}