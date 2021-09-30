package org.jetbrains.gentlewerewolf

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod

@Configuration
class SearchGateway {
    @Bean
    fun configure(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes {
            route {
                method(HttpMethod.GET)
                path("/api/proxy/**")
                uri("https://duckduckgo.com/")

                filters {
                    stripPrefix(2)
                }
            }
        }
    }
}