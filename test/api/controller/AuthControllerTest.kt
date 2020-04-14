package api.controller

import com.meetAt.config.mainModule
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import io.ktor.util.KtorExperimentalAPI
import org.junit.Test
import kotlin.test.assertEquals

class AuthControllerTest {
    @KtorExperimentalAPI
    @Test
    fun testGet() {
        withTestApplication(Application::mainModule) {
            handleRequest(HttpMethod.Get, "/get").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Called auth gateway!", response.content)
            }
        }
    }
}