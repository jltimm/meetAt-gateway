package com.meetAt.api.service

import com.meetAt.api.model.CenterRequest
import com.meetAt.api.model.LatLngRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders

class LocationService(private val url: String, private val client: HttpClient) {
    suspend fun getNearByLocations(latLngRequest: LatLngRequest): HttpResponse {
        val nearByLocationUrl = url + "nearby/nearby_locations"
        return client.get {
            url(nearByLocationUrl)
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            body = latLngRequest.toJson()
        }
    }
    suspend fun getCenter(centerRequest: CenterRequest): HttpResponse {
        val centerUrl = url + "locations/center"
        return client.get {
            url(centerUrl)
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            body = centerRequest.toJson()
        }
    }
}
