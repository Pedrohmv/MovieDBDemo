package br.com.pedrohmv

import androidx.lifecycle.Observer
import br.com.pedrohmv.MockData.filmeListMock
import br.com.pedrohmv.data.FilmeRepository
import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.ui.genero.GeneroViewModel
import br.com.pedrohmv.util.base.ErrorEvent
import br.com.pedrohmv.util.base.Event
import br.com.pedrohmv.util.base.LoadingEvent
import br.com.pedrohmv.util.base.SuccessEvent
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GeneroViewModelTest : BaseTest() {

    lateinit var viewModel: GeneroViewModel
    @Mock
    lateinit var repository: FilmeRepository
    @Mock
    lateinit var eventsView: Observer<Event>
    @Mock
    lateinit var filmesView: Observer<List<Filme>>

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)

        viewModel = GeneroViewModel(repository, TesteCoroutineScope())
        viewModel.events.observeForever(eventsView)
        viewModel.filmeList.observeForever(filmesView)
    }

    @Test
    fun obterFilmesPopulares_SuccessfulLoad() = runBlocking {

        whenever(repository.obterFilmesPopulares(28, null)).thenReturn(filmeListMock)
        viewModel.obterFilmesPopulares(28, null)

        val argEvent = argumentCaptor<Event>()
        val argFilmes = argumentCaptor<List<Filme>>()

        Mockito.verify(eventsView, Mockito.times(2)).onChanged(argEvent.capture())
        Mockito.verify(filmesView).onChanged(argFilmes.capture())

        val valuesEvents = argEvent.allValues
        val valuesFilmes = argFilmes.allValues

        Assert.assertEquals(2, valuesEvents.size)
        Assert.assertEquals(LoadingEvent::class.java, valuesEvents[0]::class.java)
        Assert.assertEquals(SuccessEvent::class.java, valuesEvents[1]::class.java)

        Assert.assertEquals(1, valuesFilmes.size)
        Assert.assertEquals(filmeListMock, valuesFilmes[0])
    }

    @Test
    fun obterFilmesPopulares_EmptyListLoad() = runBlocking {

        whenever(repository.obterFilmesPopulares(28, null)).thenReturn(emptyList())
        viewModel.obterFilmesPopulares(28, null)

        val argEvent = argumentCaptor<Event>()
        val argFilmes = argumentCaptor<List<Filme>>()

        Mockito.verify(eventsView, Mockito.times(3)).onChanged(argEvent.capture())
        Mockito.verify(filmesView).onChanged(argFilmes.capture())

        val valuesEvents = argEvent.allValues
        val valuesFilmes = argFilmes.allValues

        Assert.assertEquals(3, valuesEvents.size)
        Assert.assertEquals(LoadingEvent::class.java, valuesEvents[0]::class.java)
        Assert.assertEquals(ErrorEvent::class.java, valuesEvents[1]::class.java)
        Assert.assertEquals(SuccessEvent::class.java, valuesEvents[2]::class.java)

        Assert.assertEquals(1, valuesFilmes.size)
        Assert.assertEquals(emptyList<Filme>(), valuesFilmes[0])
    }

    @Test
    fun obterFilmesPopulares_ExceptionLoad() = runBlocking {

        whenever(repository.obterFilmesPopulares(28, null)).thenThrow(RuntimeException())
        viewModel.obterFilmesPopulares(28, null)

        val argEvent = argumentCaptor<Event>()
        val argFilmes = argumentCaptor<List<Filme>>()

        Mockito.verify(eventsView, Mockito.times(2)).onChanged(argEvent.capture())
        Mockito.verify(filmesView, Mockito.times(0)).onChanged(argFilmes.capture())

        val valuesEvents = argEvent.allValues
        val valuesFilmes = argFilmes.allValues

        Assert.assertEquals(2, valuesEvents.size)
        Assert.assertEquals(LoadingEvent::class.java, valuesEvents[0]::class.java)
        Assert.assertEquals(ErrorEvent::class.java, valuesEvents[1]::class.java)
    }

}