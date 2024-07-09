package com.example.data

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.FileReader

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
        val path = "D:\\EffectiveMobile\\data\\src\\main\\res/raw/${url.getLastPathSegment()}"
        val reader = FileReader(path)
        val response = reader.readText()
        reader.close()

        require(response.isNotEmpty()) { "JSON file $path should exist and not be empty" }

        return Response.Builder()
            .code(200)
            .message(response)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .body(ResponseBody.create(MediaType.parse("application/json"), response))
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun HttpUrl.getLastPathSegment(): String = this.pathSegments().last()
}