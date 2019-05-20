
package br.com.felipewom.routes

import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.html.body
import kotlinx.html.br
import kotlinx.html.h1
import kotlinx.html.img

fun Route.share() {
    get("/share/{id}") {
        val id = call.parameters["id"]
        call.respondHtml {
            body {
                h1 { +"Someone has shared a picture with you..." }
                br
                img(alt = id, src = "https://empreend-me.appspot.com/image/$id")
            }
        }
    }
}
