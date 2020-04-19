package api.controller

import com.meetAt.api.model.CreateLoginRequest
import com.meetAt.module
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.config.MapApplicationConfig
import io.ktor.http.*
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import io.ktor.util.KtorExperimentalAPI
import kotlin.test.Test
import kotlin.test.assertEquals

class AuthControllerTest {

    private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
    private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

    private fun createMockedClient(): HttpClient {
        return HttpClient(MockEngine) {
            engine {
                addHandler { request ->
                    when (request.url.fullUrl) {
                        "http://localhost:8082/v1/logins/create" -> {
                            // handle post
                            when (request.method) {
                                HttpMethod.Post -> {
                                    val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
                                    respond("{\"msg\":\"User created successfully\"}", headers = responseHeaders)
                                }
                                else -> error("Unhandled")
                            }
                        }
                        else -> error("Unhandled ${request.url.fullUrl}")
                    }
                }
            }
        }
    }

    @KtorExperimentalAPI
    @Test
    fun testCreateLogin() {
        withTestApplication({
            (environment.config as MapApplicationConfig).apply {
                put("ktor.service.authUrl", "http://localhost:8082/v1/")
                put("ktor.service.locationUrl", "http://localhost:8081/v1/")
            }
            module(client = createMockedClient())
        }) {
            handleRequest(HttpMethod.Post, "/logins/create") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(CreateLoginRequest("test", "test", "test").toJson())
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("{\"msg\":\"User created successfully\"}", response.content)
            }
        }
    }
}