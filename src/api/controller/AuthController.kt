package com.meetAt.api.controller

import com.meetAt.api.model.AuthRequest
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
        val body = ctx.receive<AuthRequest>()
        val response = authService.createLogin(body)
        ctx.respondText(response.readText(), ContentType.Application.Json, response.status)
    }
    suspend fun login(ctx: ApplicationCall) {
        val body = ctx.receive<AuthRequest>()
        val response = authService.login(body)
        // TODO: On success, create JWT token
        ctx.respondText(response.readText(), ContentType.Application.Json, response.status)
    }
}
