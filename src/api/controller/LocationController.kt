package com.meetAt.api.controller

import com.meetAt.api.model.NearByRequest
import com.meetAt.api.service.LocationService
import io.ktor.application.ApplicationCall
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respondText

class LocationController(private val locationService: LocationService) {
    suspend fun getNearByLocationsFromCoordinates(ctx: ApplicationCall) {
        val body = ctx.receive<NearByRequest>()
        val response = locationService.getNearByLocations(body)
        ctx.respondText(response.readText(), ContentType.Application.Json)
    }
}
