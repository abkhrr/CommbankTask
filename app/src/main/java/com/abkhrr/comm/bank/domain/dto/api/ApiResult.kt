package com.abkhrr.comm.bank.domain.dto.api

import com.abkhrr.comm.bank.utils.api.TypeError

sealed class ApiResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val message: String?, val statusCode: Int? = null, val typeError: TypeError) : ApiResult<Nothing>()
}