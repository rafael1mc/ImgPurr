package com.example.imgpurr.screen.imgur

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.imgpurr.R
import com.example.imgpurr.repository.entity.GalleryResponseModel
import kotlinx.android.synthetic.main.list_item_image.view.*

class MainAdapter(
    val items: MutableList<ListItemModel> = mutableListOf()
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_image,
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(newImages: List<GalleryResponseModel>) {
        val startPosition = items.size
        items.addAll(newImages.map { ListItemModel.fromGalleryResponseModel(it) }.flatten())
        notifyItemRangeChanged(startPosition, newImages.size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ListItemModel) = with(itemView) {
            progressBar.visibility = View.VISIBLE

            Glide.with(this)
                .load(item.url)
                .error(R.drawable.broken)
                .apply(
                    RequestOptions()
                        .override(250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?, model: Any?,
                        target: Target<Drawable>?, isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?, model: Any?,
                        target: Target<Drawable>?, dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(imageView)

        }

    }

}