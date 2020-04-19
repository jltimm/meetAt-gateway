package com.meetAt.api.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class CreateLoginRequest(val username: String, val password: String, val email: String) {
    private val mapper = jacksonObjectMapper()
    fun toJson(): String {
        return mapper.writeValueAsString(this)
    }
}
