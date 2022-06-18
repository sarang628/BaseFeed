package com.sarang.base_feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.model.ReviewImage
import com.sarang.base_feed.databinding.FragmentSimplePictureBinding
import com.sarang.base_feed.databinding.LayoutFeedVideoBinding
import java.io.File
import java.util.ArrayList

/**
 * [FeedPictureVH]
 */
class FeedPictureVpAdt(val viewModel: BaseFeedViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class MEDIA_TYPE {
        IMAGE, VIDEO
    }

    var pictures = ArrayList<ReviewImage>()
    fun setPictures(pictures: List<ReviewImage?>) {
        this.pictures = ArrayList<ReviewImage>(pictures)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return pictures.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mediaType = MEDIA_TYPE.values()[viewType]

        return when (mediaType) {
            MEDIA_TYPE.IMAGE ->
                FeedPictureVH(
                    FragmentSimplePictureBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), viewModel
                )
            else ->
                FeedVideoVH(
                    LayoutFeedVideoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), viewModel
                )
        }

    }

    override fun getItemViewType(position: Int): Int {
        val extension = File(pictures[position].picture_url).extension
        return if (extension.uppercase().contains("MP4")) MEDIA_TYPE.VIDEO.ordinal else MEDIA_TYPE.IMAGE.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FeedPictureVH)
            holder.setPicture(pictures[position])
        else if (holder is FeedVideoVH)
            holder.setPicture(pictures[position])
    }
}