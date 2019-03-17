package br.com.pedrohmv.ui.detalhe

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrohmv.R
import br.com.pedrohmv.di.THUMB_URL
import br.com.pedrohmv.domain.Video
import br.com.pedrohmv.util.inflate
import br.com.pedrohmv.util.loadImageUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_video_filme.view.*

class VideoAdapter(
    private val videoList: List<Video>,
    private val onVideoClick: (String) -> Unit
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_video_filme))

    override fun getItemCount() = videoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(videoList[position], onVideoClick)


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(video: Video, onVideoClick: (String) -> Unit) {
            with(itemView) {
                videoThumbImageView.loadImageUrl("$THUMB_URL${video.key}/0.jpg")
                setOnClickListener { onVideoClick(video.key) }
            }
        }
    }
}