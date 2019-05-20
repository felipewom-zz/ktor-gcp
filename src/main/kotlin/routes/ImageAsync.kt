package br.com.felipewom.routes


import br.com.felipewom.StorageResult
import br.com.felipewom.loadFromStorage
import br.com.felipewom.saveToStorage
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.put
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun Route.convertToMonoChromeAsync() {
    put("/mono/{id}") {
        val id = call.parameters["id"]
        val imageId = "demo/$id"
        val loadResult = call.loadFromStorage("empreend-me", imageId)
        when (loadResult) {
            is StorageResult.LoadSuccess -> {
                withContext(Dispatchers.IO) {
                    val monochrome = monochrome(loadResult.data)
                    val saveResult = call.saveToStorage("empreend-me", imageId, monochrome)
                    when (saveResult) {
                        is StorageResult.SaveSucess -> call.respond(HttpStatusCode.OK)
                        is StorageResult.Failure -> call.respond(HttpStatusCode.InternalServerError, saveResult.message)
                    }
                }
            }
            is StorageResult.Failure -> {
                call.respond(HttpStatusCode.InternalServerError, loadResult.message)
            }
        }
    }
}