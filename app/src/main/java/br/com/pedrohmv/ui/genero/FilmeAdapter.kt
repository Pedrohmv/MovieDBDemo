package br.com.pedrohmv.ui.genero

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrohmv.R
import br.com.pedrohmv.di.IMAGE_URL
import br.com.pedrohmv.domain.Filme
import br.com.pedrohmv.util.inflate
import br.com.pedrohmv.util.loadImageUrl
import kotlinx.android.synthetic.main.item_filme.view.*

class FilmeAdapter(
    private val filmeList: List<Filme>,
    private val onItemClick: (View, Filme) -> Unit
) : RecyclerView.Adapter<FilmeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_filme))

    override fun getItemCount() = filmeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(filmeList[position], onItemClick)


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(filme: Filme, onItemClick: (View, Filme) -> Unit) {
            with(itemView) {
                tituloTextView.text = filme.titulo
                val posterUrl = "$IMAGE_URL${filme.caminhoPoster}"
                posterImageView.loadImageUrl(posterUrl)
                itemView.setOnClickListener { onItemClick(posterImageView, filme) }
            }
        }
    }
}