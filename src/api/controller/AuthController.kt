package com.meetAt.api.controller

import com.meetAt.api.model.CreateLoginRequest
import com.meetAt.api.service.AuthService
import io.ktor.application.ApplicationCall
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
class AuthController(private val authService: AuthService) {
    suspend fun create(ctx: ApplicationCall) {
        val body = ctx.receive<CreateLoginRequest>()
        val response = authService.createLogin(body)
        ctx.respondText(response.readText(), ContentType.Application.Json, response.status)
    }
}
