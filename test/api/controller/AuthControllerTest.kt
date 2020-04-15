package api.controller

import com.meetAt.module
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
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/get").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Called auth gateway!", response.content)
            }
        }
    }
}