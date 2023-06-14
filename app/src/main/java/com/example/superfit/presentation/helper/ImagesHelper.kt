package com.example.superfit.presentation.helper

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

object ImagesHelper {

    fun getCapturedImage(contentResolver: ContentResolver, selectedImage: Uri): Bitmap {
        return when {
            Build.VERSION.SDK_INT < 28 -> MediaStore.Images.Media.getBitmap(
                contentResolver,
                selectedImage
            )

            else -> {
                val source =
                    ImageDecoder.createSource(contentResolver, selectedImage)
                ImageDecoder.decodeBitmap(source)
            }
        }
    }

    fun getResizedBitmap(bm: Bitmap): Bitmap? {
        val maxHeight = 512
        val maxWidth = 512
        val scale: Float =
            (maxHeight.toFloat() / bm.width).coerceAtMost(maxWidth.toFloat() / bm.height)

        val matrix = Matrix()
        matrix.postScale(scale, scale)
        return Bitmap.createBitmap(bm, 0, 0, bm.width, bm.height, matrix, true)
    }
}