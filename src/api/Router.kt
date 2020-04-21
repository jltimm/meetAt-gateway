package com.meetAt.api

import com.meetAt.api.controller.AuthController
import com.meetAt.api.controller.LocationController
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
fun Routing.auth(authController: AuthController) {
    route("auth") {
        route("create") {
            post { authController.create(this.context) }
        }
        route("login") {
            post { authController.login(this.context) }
        }
    }
}

fun Routing.location(locationController: LocationController) {
    route("nearby") {
        route("nearby_locations") {
            get { locationController.getNearByLocationsFromCoordinates(this.context) }
        }
    }
    route("locations") {
        route("center") {
            get { locationController.getCenter(this.context) }
        }
    }
}
