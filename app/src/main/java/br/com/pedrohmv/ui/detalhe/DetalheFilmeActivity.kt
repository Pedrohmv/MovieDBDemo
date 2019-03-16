package br.com.pedrohmv.ui.detalhe

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pedrohmv.R
import br.com.pedrohmv.di.IMAGE_URL
import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.domain.Video
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe_filme.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalheFilmeActivity : AppCompatActivity() {

    private val viewModel by viewModel<DetalheFilmeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_filme)
        ActivityCompat.postponeEnterTransition(this);
        intent.extras?.let {
            val filme = it.getParcelable("FILME") as Filme
            preencherDadosFilme(filme)
            toolbar.title = filme.titulo
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun preencherDadosFilme(filme: Filme) {
        Picasso.with(this).load("$IMAGE_URL${filme.caminhoPoster}")
            .fit()
            .centerCrop()
            .into(posterImageView, object : Callback {
                override fun onSuccess() {
                    ActivityCompat.startPostponedEnterTransition(this@DetalheFilmeActivity);
                }

                override fun onError() {
                    ActivityCompat.startPostponedEnterTransition(this@DetalheFilmeActivity);
                }

            })

        sinopseTextView.text = filme.sinopse

        viewModel.filmesSimilares.observe(this, Observer { filmes ->
            filmes?.let {
                setupFilmesSimilaresRecyclerView(filmes)
            }
        })

        viewModel.videos.observe(this, Observer { videos ->
            videos?.let {
                setupVideosRecyclerView(videos)
            }
        })

        viewModel.obterVideosFilme(filme.id)

        viewModel.obterFilmesSimilares(filme.id)
    }

    private fun setupFilmesSimilaresRecyclerView(filmes: List<Filme>) {
        filmesSimilaresRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        filmesSimilaresRecyclerView.adapter = FilmeSimilarAdapter(filmes)

    }

    private fun setupVideosRecyclerView(videos: List<Video>) {
        videosRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        videosRecyclerView.adapter = VideoAdapter(videos)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishAfterTransition()
        }
        return true
    }

}