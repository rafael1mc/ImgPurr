package com.example.imgpurr.repository.api.imgur

import com.example.imgpurr.repository.entity.GallerySearchResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurAPI {

    @GET("gallery/search/time/{page}/?q_type=png")
    suspend fun searchPhoto(
        @Path("page") page: Int,
        @Query("q_all") search: String
    ): Response<GallerySearchResponseModel>

}