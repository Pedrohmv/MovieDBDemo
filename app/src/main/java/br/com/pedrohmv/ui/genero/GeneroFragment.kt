package br.com.pedrohmv.ui.genero


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrohmv.R
import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.ui.MainActivity
import br.com.pedrohmv.ui.detalhe.DetalheFilmeActivity
import br.com.pedrohmv.util.base.ErrorEvent
import br.com.pedrohmv.util.base.LoadingEvent
import br.com.pedrohmv.util.base.SuccessEvent
import br.com.pedrohmv.util.inflate
import br.com.pedrohmv.util.toast
import kotlinx.android.synthetic.main.fragment_genero.*
import kotlinx.android.synthetic.main.partial_loading_filme.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeneroFragment : Fragment() {

    private val viewModel by viewModel<GeneroViewModel>()
    private var idGenero: Int? = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_genero)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingLayout.startShimmerAnimation()

        arguments?.let {
            idGenero = it.getInt("ID_GENERO")
            if (idGenero == 0){
                idGenero = null
                setSearchListener()
            }
            viewModel.filmeList.observe(this, Observer { filmes ->
                filmes?.let {
                    setupFilmeRecyclerView(filmes)
                }
            })

            viewModel.events.observe(this, Observer { event ->
                event?.let {
                    when (event){
                        is LoadingEvent -> loadingLayout.visibility = View.VISIBLE
                        is ErrorEvent -> toast(event.errorMessage)
                        is SuccessEvent -> {
                            loadingLayout.visibility = View.GONE
                            filmeRecyclerView.visibility = View.VISIBLE
                        }
                    }
                }
            })

            viewModel.obterFilmesPopulares(idGenero)

        }
    }

    private fun setSearchListener() {
        (activity as MainActivity).searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.obterFilmesPopulares(idGenero, newText)
                return true
            }

        })
    }

    private fun setupFilmeRecyclerView(filmes: List<Filme>) {

        val adapter = FilmeAdapter(filmes) { sharedView, filme ->
            val intent = Intent(activity, DetalheFilmeActivity::class.java).apply {
                putExtra("FILME", filme)
            }

            val option = ActivityOptions.makeSceneTransitionAnimation(activity, sharedView, "posterFilmeTransition")
            startActivity(intent, option.toBundle())
        }

        filmeRecyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        filmeRecyclerView.adapter = adapter
    }

    companion object {

        operator fun invoke(genreId: Int) = GeneroFragment().apply {
            arguments = Bundle().apply {
                putInt("ID_GENERO", genreId)
            }
        }

    }

}
