package com.example.imgpurr.screen.imgur

import com.example.imgpurr.repository.api.imgur.ImgurRepository
import com.example.imgpurr.screen.BaseViewModel
import com.example.imgpurr.util.StringUtil

class MainViewModel(
    private val imgurRepository: ImgurRepository,
    private val stringUtils: StringUtil
) : BaseViewModel() {

    val model = MainModel()

}