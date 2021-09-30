package org.jetbrains.gentlewerewolf

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
class GentleWerewolfApplicationTests(
    private val salesService: SalesService
) {
    lateinit var client: WebTestClient

    @BeforeEach
    fun setUp(context: ApplicationContext) {
        client = WebTestClient.bindToApplicationContext(context).build()
    }

    @Test
    fun getHello() {
        client.get()
            .uri("/api/proxy/?q=hello")
            .exchange()
            .expectStatus().is2xxSuccessful
    }

    @Test
    fun postData() {
        client.post()
            .uri("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(
                """
                {
                    "price": 100500,
                    "user": {
                        "login": "admin",
                        "priority": 1
                    }
                }
                """
            )
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody().json(
                """
                {
                  "price": 100500
                }
                """
            )
    }

    @Test
    fun jsonAssert() {
        JSONAssert.assertEquals(
            """
            {'price': 100}
            """,
            salesService.generateSalesJson(),
            false
        )
    }
}
