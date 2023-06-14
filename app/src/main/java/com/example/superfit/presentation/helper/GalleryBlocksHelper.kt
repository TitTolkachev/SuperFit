package com.example.superfit.presentation.helper

import com.example.superfit.presentation.view.model.GalleryBlock
import com.example.superfit.presentation.view.model.Photo
import java.time.format.DateTimeFormatter

private data class MutableGalleryBlock(
    val images: MutableList<Photo> = mutableListOf(),
    val title: String = ""
)

object GalleryBlocksHelper {

    fun mapPhotosToGalleryBlocks(photos: List<Pair<Long, Photo>>): List<GalleryBlock> {

        val formatter = DateTimeFormatter.ofPattern("MMMM")
        val res = mutableListOf<MutableGalleryBlock>()

        photos.sortedBy { it.first }

        var month = 0
        var year = 0
        photos.forEach {
            val photoDateTime = PhotoDateMapper.convertSecondsToLocalDateTime(it.first)

            if (photoDateTime.year == year && photoDateTime.monthValue == month) {
                res.last().images.add(it.second)
            } else {
                res.add(
                    MutableGalleryBlock(
                        mutableListOf(it.second),
                        "${formatter.format(photoDateTime)}, $year"
                    )
                )
            }

            year = photoDateTime.year
            month = photoDateTime.monthValue
        }

        return res.map { GalleryBlock(it.images.toList(), it.title) }
    }
}