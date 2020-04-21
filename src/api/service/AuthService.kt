package com.meetAt.api.service

import com.meetAt.api.model.AuthRequest
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
    suspend fun createLogin(authRequest: AuthRequest): HttpResponse {
        val createUrl = url + "auth/create"
        return client.post {
            url(createUrl)
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            body = authRequest.toJson()
        }
    }
    suspend fun login(authRequest: AuthRequest): HttpResponse {
        val loginUrl = url + "auth/login"
        return client.post {
            url(loginUrl)
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            body = authRequest.toJson()
        }
    }
}
