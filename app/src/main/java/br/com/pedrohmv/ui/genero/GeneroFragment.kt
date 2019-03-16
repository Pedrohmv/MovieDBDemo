package br.com.pedrohmv.ui.genero


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrohmv.R
import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.ui.detalhe.DetalheFilmeActivity
import br.com.pedrohmv.util.inflate
import kotlinx.android.synthetic.main.fragment_genero.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeneroFragment : Fragment() {

    private val viewModel by viewModel<GeneroViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_genero)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val idGenero = it.getInt("ID_GENERO")

            viewModel.filmeList.observe(this, Observer { filmes ->
                filmes?.let {
                    val filmeGenero = it.filter { it.listaIdGenero.contains(idGenero) }
                    setupFilmeRecyclerView(filmeGenero)
                }
            })

            viewModel.obterFilmesPopulares()
        }
    }

    private fun setupFilmeRecyclerView(filmes: List<Filme>) {
        filmeRecyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        filmeRecyclerView.adapter = FilmeAdapter(filmes) { sharedView, filme ->
            val intent = Intent(activity, DetalheFilmeActivity::class.java).apply {
                putExtra("FILME", filme)
            }

            val option = ActivityOptions.makeSceneTransitionAnimation(activity, sharedView, "posterFilmeTransition")
            startActivity(intent, option.toBundle())
        }
    }

}
