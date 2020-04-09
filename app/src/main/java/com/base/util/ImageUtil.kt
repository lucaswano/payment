package com.base.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import com.base.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.Target


object ImageUtil {

    fun loadAvatarImage(context: Context,
                        url: String,
                        placeHolder: Int = R.drawable.dummy_circle_avatar,
                        imageView: ImageView,
                        height: Int = Target.SIZE_ORIGINAL,
                        width: Int = Target.SIZE_ORIGINAL) {
        Glide.with(context).load(url).asBitmap().centerCrop()
                .placeholder(placeHolder).override(height, width).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
    }

    fun loadAvatarImage(context: Context,
                        uri: Uri,
                        placeHolder: Int = R.drawable.dummy_circle_avatar,
                        imageView: ImageView,
                        height: Int = Target.SIZE_ORIGINAL,
                        width: Int = Target.SIZE_ORIGINAL) {
        Glide.with(context).load(uri).asBitmap().centerCrop()
                .placeholder(placeHolder).override(height, width).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
    }

    fun loadCircleAvatarImage(context: Context,
                              url: String,
                              placeHolder: Int = R.drawable.dummy_circle_avatar,
                              imageView: ImageView,
                              height: Int = Target.SIZE_ORIGINAL,
                              width: Int = Target.SIZE_ORIGINAL) {
        Glide.with(context).load(url).asBitmap().centerCrop()
                .placeholder(placeHolder).override(height, width).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : BitmapImageViewTarget(imageView) {
                    override fun setResource(resource: Bitmap) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, resource)
                        circularBitmapDrawable.isCircular = true
                        imageView.setImageDrawable(circularBitmapDrawable)
                    }
                })
    }

    fun loadCircleAvatarImage(context: Context,
                              uri: Uri,
                              placeHolder: Int = R.drawable.dummy_circle_avatar,
                              imageView: ImageView,
                              height: Int = Target.SIZE_ORIGINAL,
                              width: Int = Target.SIZE_ORIGINAL) {
        Glide.with(context).load(uri).asBitmap().centerCrop()
                .placeholder(placeHolder).override(height, width).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : BitmapImageViewTarget(imageView) {
                    override fun setResource(resource: Bitmap) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, resource)
                        circularBitmapDrawable.isCircular = true
                        imageView.setImageDrawable(circularBitmapDrawable)
                    }
                })
    }

    fun loadGifImage(context: Context,
                     url: String,
                     placeHolder: Int = R.drawable.dummy_circle_avatar,
                     imageView: ImageView,
                     height: Int = Target.SIZE_ORIGINAL,
                     width: Int = Target.SIZE_ORIGINAL) {
        Glide.with(context).load(url).asGif().centerCrop()
                .placeholder(placeHolder).override(height, width).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
    }

    fun loadGifImage(context: Context,
                     uri: Uri,
                     placeHolder: Int = R.drawable.dummy_circle_avatar,
                     imageView: ImageView,
                     height: Int = Target.SIZE_ORIGINAL,
                     width: Int = Target.SIZE_ORIGINAL) {
        Glide.with(context).load(uri).asGif().centerCrop()
                .placeholder(placeHolder).override(height, width).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
    }
}