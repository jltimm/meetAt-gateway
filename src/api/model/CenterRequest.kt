package com.meetAt.api.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class CenterRequest(val locations: List<LatLngRequest>) {
    private val mapper = jacksonObjectMapper()
    fun toJson(): String {
        return mapper.writeValueAsString(this)
    }
}
