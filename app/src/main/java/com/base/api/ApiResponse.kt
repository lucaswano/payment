package com.base.api

/**
 * @author HungHN on 3/15/2018.
 */

data class ApiResponse<T>(var code: Int, var data: T?) {

    val isSuccess: Boolean = (code == 0)
}
