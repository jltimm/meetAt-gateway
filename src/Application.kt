package com.meetAt

import com.fasterxml.jackson.databind.SerializationFeature
import com.meetAt.api.auth
import com.meetAt.api.controller.AuthController
import com.meetAt.api.service.AuthService
import io.ktor.application.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(client: HttpClient = HttpClient(CIO)) {
    val authService = AuthService(environment.config.property("ktor.service.authUrl").getString(), client)
    install(Routing) {
        auth(AuthController(authService))
    }
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
}

