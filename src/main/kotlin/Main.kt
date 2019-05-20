package br.com.felipewom

import br.com.felipewom.routes.convertToMonoChrome
import br.com.felipewom.routes.home
import br.com.felipewom.routes.listData
import br.com.felipewom.routes.share
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.routing

fun Application.main() {

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            serializeNulls()
        }
    }

    routing {

        home()
        convertToMonoChrome()
        listData()
        share()
        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }
    }
}
