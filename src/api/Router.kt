package com.meetAt.api

import com.meetAt.api.controller.AuthController
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.auth(authController: AuthController) {
    route("get") {
        get { authController.get(this.context) }
    }
}