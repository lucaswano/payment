package com.project.api.errors

import com.base.api.errors.BaseError

/**
 * @author HungHN on 3/15/2018.
 */
data class ParserError(override val requestId: String,
                       override val response: String = "Parser error") : BaseError(requestId, response)
