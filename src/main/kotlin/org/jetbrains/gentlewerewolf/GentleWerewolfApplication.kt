package org.jetbrains.gentlewerewolf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GentleWerewolfApplication

fun main(args: Array<String>) {
    runApplication<GentleWerewolfApplication>(*args)
}
