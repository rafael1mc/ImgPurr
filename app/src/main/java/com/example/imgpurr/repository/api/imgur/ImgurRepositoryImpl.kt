package com.example.imgpurr.repository.api.imgur

import com.example.imgpurr.repository.BaseRepository
import com.example.imgpurr.repository.RetrofitBuilder
import com.example.imgpurr.repository.entity.GallerySearchResponseModel

class ImgurRepositoryImpl : BaseRepository(), ImgurRepository {

    override suspend fun searchImages(
        page: Int,
        search: String
    ): Result<GallerySearchResponseModel> {

        return handleResponse {
            RetrofitBuilder()
                .createService(ImgurAPI::class.java)
                .searchPhoto(page, search)
        }

    }
}