package com.meetAt.api.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class NearByRequest(val lat: Double, val lng: Double) {
    private val mapper = jacksonObjectMapper()
    fun toJson(): String {
        return mapper.writeValueAsString(this)
    }
}