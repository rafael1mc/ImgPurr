package com.example.imgpurr.screen.imgur

import androidx.lifecycle.viewModelScope
import com.example.imgpurr.R
import com.example.imgpurr.repository.ForbiddenException
import com.example.imgpurr.repository.UnauthorizedException
import com.example.imgpurr.repository.api.imgur.ImgurRepository
import com.example.imgpurr.screen.BaseViewModel
import com.example.imgpurr.util.StringUtil
import kotlinx.coroutines.launch

class MainViewModel(
    private val imgurRepository: ImgurRepository,
    private val stringUtils: StringUtil
) : BaseViewModel() {

    val model = MainModel()

    fun load(fromBeginning: Boolean = false) {
        viewModelScope.launch {
            if (fromBeginning) {
                model.page = 0
            }
            model.stateOb.value = MainModel.ImgurState.Loading

            val response = imgurRepository.searchImages(++model.page, SEARCH)
            val resultData = handleResponse(response, ::handleError)

            resultData?.let {
                model.stateOb.value = MainModel.ImgurState.Loaded(resultData.data)
            }

        }
    }

    private fun handleError(error: Throwable?) {
        when (error) {
            is UnauthorizedException, is ForbiddenException -> {
                model.stateOb.value = MainModel.ImgurState.Error(
                    error.message
                        ?: stringUtils.getFromResources(R.string.you_don_t_have_permission_to_do_perform_this_action__Please__check_your_token)
                )
            }
            else -> {
                model.stateOb.value = MainModel.ImgurState.Error(
                    error?.message
                        ?: stringUtils.getFromResources(R.string.something_went_wrong_performing_this_action)
                )
            }
        }
    }


    companion object {
        private const val SEARCH = "cats"
    }
}