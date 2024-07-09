package com.example.data

import android.app.Application
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class MockInterceptor @Inject constructor(val _application: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val context = _application.applicationContext
        val url = chain.request().url()
        val path = "raw/${url.getLastPathSegment()}"
        val resourceId = context.resources.getIdentifier(path, "raw", context.packageName)

        val inputStream = context.resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val response = reader.readText()
        reader.close()

        require(response.isNotEmpty()) { "JSON file $path should exist and not be empty" }

//        val path = "res/raw/${url.getLastPathSegment()}"
//        val reader = FileReader(path)
//        val response = reader.readText()
//        reader.close()
//
//        require(response.isNotEmpty()) { "JSON file $path should exist and not be empty" }

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