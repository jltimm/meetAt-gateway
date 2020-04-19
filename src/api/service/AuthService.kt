package com.meetAt.api.service

import com.meetAt.api.model.CreateLogin
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
class AuthService(private val url: String, private val client: HttpClient) {

    suspend fun createLogin(login: CreateLogin): HttpResponse {
        val createUrl = url + "create"
        return client.post {
            url(createUrl)
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            body = login.toJson()
        }
    }

    fun destroy() {
        client.close()
    }
}