package com.example.imgpurr.screen.imgur

import com.example.imgpurr.repository.entity.GalleryResponseModel

class ListItemModel(val url: String) {
    companion object {

        fun fromGalleryResponseModel(item: GalleryResponseModel): List<ListItemModel> {
            val list = mutableListOf<ListItemModel>()
            if (item.isAlbum) {
                list.addAll(item.images?.map { ListItemModel(it.link) } ?: listOf())
            } else {
                list.add(ListItemModel(item.link ?: ""))
            }
            return list
        }

    }
}