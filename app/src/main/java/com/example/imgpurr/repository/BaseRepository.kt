package com.example.imgpurr.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseRepository {

    protected suspend fun <T> handleResponse(call: suspend () -> Response<T>): Result<T> {
        return withContext<Result<T>>(Dispatchers.IO) {
            try {
                val response = call()
                if (response.isSuccessful) {
                    val parsedBody = response.body() ?: throw NullPointerException()
                    Result.success(parsedBody)
                } else {
                    when (response.code()) {
                        401 -> throw UnauthorizedException()
                        404 -> throw NotFoundException()
                        403 -> throw ForbiddenException()
                        500 -> throw InternalServerErrorException()
                        else -> throw OtherException()
                    }
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}

class UnauthorizedException(msg: String? = null) : Exception(msg)
class NotFoundException(msg: String? = null) : Exception(msg)
class ForbiddenException(msg: String? = null) : Exception(msg)
class InternalServerErrorException(msg: String? = null) : Exception(msg)
class OtherException(msg: String? = null) : Exception(msg)