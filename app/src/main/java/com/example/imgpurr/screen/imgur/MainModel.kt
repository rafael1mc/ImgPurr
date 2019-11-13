package com.example.imgpurr.screen.imgur

import androidx.lifecycle.MutableLiveData
import com.example.imgpurr.repository.entity.GalleryResponseModel

class MainModel {
    val stateOb = MutableLiveData<ImgurState>()
    var page = 0

    sealed class ImgurState {
        object Loading : ImgurState()
        data class Loaded(val searchResult: List<GalleryResponseModel>?) : ImgurState()
        data class Error(val message: String) : ImgurState()
    }
}