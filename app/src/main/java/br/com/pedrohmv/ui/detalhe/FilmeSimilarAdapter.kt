package br.com.pedrohmv.ui.detalhe

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrohmv.R
import br.com.pedrohmv.di.IMAGE_URL
import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.util.inflate
import br.com.pedrohmv.util.loadImageUrl
import kotlinx.android.synthetic.main.item_filme_similar.view.*

class FilmeSimilarAdapter(
    private val filmeList: List<Filme>
) : RecyclerView.Adapter<FilmeSimilarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_filme_similar))

    override fun getItemCount() = filmeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(filmeList[position])


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(filme: Filme) {
            with(itemView) {
                val posterUrl = "$IMAGE_URL${filme.caminhoPoster}"
                posterFilmeImageView.loadImageUrl(posterUrl)
            }
        }
    }
}