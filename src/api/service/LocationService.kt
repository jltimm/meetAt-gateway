package com.meetAt.api.service

import com.meetAt.api.model.NearByRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders

class LocationService(private val url: String, private val client: HttpClient) {
    suspend fun getNearByLocations(nearByRequest: NearByRequest): HttpResponse {
        val nearByLocationUrl = url + "nearby/nearby_locations"
        return client.get {
            url(nearByLocationUrl)
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            body = nearByRequest.toJson()
        }
    }
}
