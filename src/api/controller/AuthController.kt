package com.meetAt.api.controller

import io.ktor.application.ApplicationCall
import io.ktor.response.respondText

class AuthController {
    suspend fun get(ctx: ApplicationCall) {
        ctx.respondText("Called auth gateway!")
    }
}