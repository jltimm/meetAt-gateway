package com.meetAt.api.controller

import com.meetAt.api.model.CenterRequest
import com.meetAt.api.model.LatLngRequest
import com.meetAt.api.service.LocationService
import io.ktor.application.ApplicationCall
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respondText

class LocationController(private val locationService: LocationService) {
    suspend fun getNearByLocationsFromCoordinates(ctx: ApplicationCall) {
        val body = ctx.receive<LatLngRequest>()
        val response = locationService.getNearByLocations(body)
        ctx.respondText(response.readText(), ContentType.Application.Json)
    }
    suspend fun getCenter(ctx: ApplicationCall) {
        val body = ctx.receive<CenterRequest>()
        val response = locationService.getCenter(body)
        ctx.respondText(response.readText(), ContentType.Application.Json)
    }
}
