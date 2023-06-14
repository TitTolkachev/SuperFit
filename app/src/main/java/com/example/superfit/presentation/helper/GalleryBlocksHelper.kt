package com.example.superfit.presentation.helper

import com.example.superfit.presentation.view.model.GalleryBlock
import com.example.superfit.presentation.view.model.Photo
import java.time.format.DateTimeFormatter

private data class MutableGalleryBlock(
    val images: MutableList<Photo> = mutableListOf(),
    val title: String = ""
)

object GalleryBlocksHelper {

    private const val GALLERY_DISPLAY_MODE = 2

    fun mapPhotosToGalleryBlocks(photos: List<Pair<Long, Photo>>): List<GalleryBlock> {

        // TODO(Выбрать нужный формат даты в галерее)
        val formatter1 = DateTimeFormatter.ofPattern("MMMM")
        val formatter2 = DateTimeFormatter.ofPattern("dd MMMM")
        val res = mutableListOf<MutableGalleryBlock>()

        photos.sortedBy { it.first }

        var day = 0
        var month = 0
        var year = 0
        photos.forEach {
            val photoDateTime = PhotoDateMapper.convertSecondsToLocalDateTime(it.first)

            if (photoDateTime.year == year &&
                photoDateTime.monthValue == month &&
                (GALLERY_DISPLAY_MODE == 1 || photoDateTime.dayOfMonth == day)
            ) {
                res.last().images.add(it.second)
            } else {
                res.add(
                    MutableGalleryBlock(
                        mutableListOf(it.second),
                        if (GALLERY_DISPLAY_MODE == 1)
                            "${formatter1.format(photoDateTime)}, ${photoDateTime.year}"
                        else
                            "${formatter2.format(photoDateTime)}, ${photoDateTime.year}"
                    )
                )
            }

            day = photoDateTime.dayOfMonth
            year = photoDateTime.year
            month = photoDateTime.monthValue
        }

        return res.map { GalleryBlock(it.images.toList(), it.title) }
    }
}