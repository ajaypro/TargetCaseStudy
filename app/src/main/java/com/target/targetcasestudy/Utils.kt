package com.target.targetcasestudy

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.ui.deallist.DealListStatus

/**
 * Glide image loading based on imgurl
 * @param imgUrl - url to fetch the image
 * @param imageView - view on which to load image
 */
fun loadImage(imageView: ImageView, imgUrl: String?){

    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imageView)

    }
}

/**
 * Extension function to check description is valid, to avoid showing users some irrelevant text.
 */
fun String.isValidPharse(): Boolean {

    if (this.length < 2) {
       return false
    }

    val subString = StringBuilder()

    for (i in 0..this.length/2){
        subString.append(this[i])

        val clearedFromSubstrings = this.replace(subString.toString().toRegex(), "")
        if(clearedFromSubstrings.isEmpty()){
            return true
        }
    }
    return false
}

fun bindStatus(statusImageView: ImageView, status: DealListStatus?) {
    when (status) {
        DealListStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        DealListStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        DealListStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}