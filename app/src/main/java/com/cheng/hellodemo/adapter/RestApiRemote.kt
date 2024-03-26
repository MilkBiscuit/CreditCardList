package com.cheng.hellodemo.adapter

import com.cheng.hellodemo.domain.adapterinterface.IRestApiRemote
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RestApiRemote @Inject constructor() : IRestApiRemote {

    private val client: HttpClient by lazy {
        HttpClient(OkHttp) {
            followRedirects = true
            expectSuccess = false
            install(HttpTimeout)

            HttpResponseValidator {
                validateResponse { response ->
                    val statusCode = response.status.value
                    if (statusCode !in 200..299) {
                        throw RuntimeException("Status code is $statusCode!")
                    }
                }
            }
        }
    }

    override suspend fun get(url: String, headers: Map<String, String>): Result<String> {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val httpResponse: HttpResponse = client.get(url) {
                    headers {
                        headers.forEach { mapEntry ->
                            append(mapEntry.key, mapEntry.value)
                        }
                    }
                }

                httpResponse.body()
            }
        }
    }

}
