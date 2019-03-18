package br.com.pedrohmv.di

import br.com.pedrohmv.data.FilmeRepository
import br.com.pedrohmv.data.FilmeRepositoryImpl
import br.com.pedrohmv.ui.detalhe.DetalheFilmeViewModel
import br.com.pedrohmv.ui.genero.GeneroViewModel
import br.com.pedrohmv.util.base.AppCoroutineScope
import br.com.pedrohmv.util.base.MainCoroutineScope
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModules = module {

    single { FilmeRepositoryImpl(get()) as FilmeRepository }

    factory { MainCoroutineScope() as AppCoroutineScope }

    viewModel { GeneroViewModel(get(), get()) }

    viewModel { DetalheFilmeViewModel(get(), get()) }

}