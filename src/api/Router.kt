package com.meetAt.api

import com.meetAt.api.controller.AuthController
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
fun Routing.auth(authController: AuthController) {
    route("/create") {
        post { authController.create(this.context) }
    }
}